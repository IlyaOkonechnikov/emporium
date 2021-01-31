package com.emporium.lib.auth.config.jwt;

import com.emporium.lib.auth.config.CustomUserDetails;
import com.emporium.lib.auth.config.CustomUserDetailsService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {

  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String BEARER_PREFIX = "Bearer";

  private final JwtProvider jwtProvider;
  private final CustomUserDetailsService customUserDetailsService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    try {
      String token = getTokenFromRequest((HttpServletRequest) request);
      if (!token.isEmpty()) {
        String userLogin = jwtProvider.getLoginFromToken(token);
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null,
            customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception e) {
      PrintWriter out = response.getWriter();
      out.print(e.getMessage());
      out.flush();
      return;
    }
    chain.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION_HEADER);
    return StringUtils.hasText(bearer) && bearer.startsWith(BEARER_PREFIX) ? bearer.substring(7) : "";
  }
}