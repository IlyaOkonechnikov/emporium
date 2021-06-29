package com.emporium.lib.auth.configuration.jwt;

import com.emporium.lib.auth.data.jpa.Token;
import com.emporium.lib.auth.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

  public static final String REQUEST_AUTH_ERROR_MSG = "Unable to authorize request";
  //  todo: move to variables
  private static final String JWT_SECRET = "jwtSecret";

  private final TokenRepository repository;

  public String generateToken(String login) {
    final String token =
        Jwts.builder()
            .setIssuedAt(new Date())
            .setSubject(login)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact();
    repository.save(Token.of(token));
    return token;
  }

  public String getUsernameFromToken(String token) {
    final Token savedToken =
        repository
            .findByValue(token)
            .orElseThrow(() -> new IllegalArgumentException(REQUEST_AUTH_ERROR_MSG));
    try {
      return Jwts.parser()
          .setSigningKey(JWT_SECRET)
          .parseClaimsJws(savedToken.getValue())
          .getBody()
          .getSubject();
    } catch (Exception e) {
      throw new IllegalArgumentException(REQUEST_AUTH_ERROR_MSG);
    }
  }

  public void invalidateToken(HttpServletRequest request) {
    final Token token =
        repository
            .findByValue(JwtUtil.getTokenFromRequest(request))
            .orElseThrow(() -> new IllegalArgumentException(REQUEST_AUTH_ERROR_MSG));
    repository.delete(token);
  }
}
