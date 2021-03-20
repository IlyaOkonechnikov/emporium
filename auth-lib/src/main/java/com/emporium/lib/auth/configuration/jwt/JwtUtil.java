package com.emporium.lib.auth.configuration.jwt;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtil {

  private final String AUTHORIZATION_HEADER = "Authorization";
  private final String BEARER_PREFIX = "Bearer";

  public String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION_HEADER);
    return StringUtils.hasText(bearer) && bearer.startsWith(BEARER_PREFIX) ? bearer.substring(7) : "";
  }
}
