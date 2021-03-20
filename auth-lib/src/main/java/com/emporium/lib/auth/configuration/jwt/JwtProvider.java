package com.emporium.lib.auth.configuration.jwt;

import com.emporium.lib.auth.data.jpa.Token;
import com.emporium.lib.auth.repository.TokenRepository;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

  public static final String TOKEN_PARSING_ERROR_MSG = "An error occurred while parsing the token";
  private static final String JWT_SECRET = "jwtSecret";

  private final TokenRepository repository;

  public String generateToken(String login) {
    final String token = Jwts.builder()
        .setIssuedAt(new Date())
        .setSubject(login)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .compact();
    repository.save(Token.of(token));
    return token;
  }

  public String getUsernameFromToken(String token) {
    try {
      return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
    } catch (Exception e) {
      throw new IllegalArgumentException(TOKEN_PARSING_ERROR_MSG);
    }
  }

  public void invalidateToken(HttpServletRequest request) {
    repository.delete(repository.findByValue(JwtUtil.getTokenFromRequest(request)));
  }
}
