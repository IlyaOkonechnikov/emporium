package com.emporium.lib.auth.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

  public static final String TOKEN_PARSING_ERROR_MSG = "An error occurred while parsing the token";

  @Value("$(jwt.secret)")
  private String jwtSecret;

  public String generateToken(String login) {
    Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
    return Jwts.builder()
        .setIssuedAt(new Date())
        .setSubject(login)
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getLoginFromToken(String token) {
    try {
      return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    } catch (Exception e) {
      throw new IllegalArgumentException(TOKEN_PARSING_ERROR_MSG);
    }
  }
}
