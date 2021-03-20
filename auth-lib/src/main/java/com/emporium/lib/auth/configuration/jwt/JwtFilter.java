package com.emporium.lib.auth.configuration.jwt;

import com.emporium.lib.auth.configuration.CustomUserDetails;
import com.emporium.lib.auth.configuration.CustomUserDetailsService;

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

  private final JwtProvider jwtProvider;
  private final CustomUserDetailsService customUserDetailsService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    try {
      String token = JwtUtil.getTokenFromRequest((HttpServletRequest) request);
      if (!token.isEmpty()) {
        String username = jwtProvider.getUsernameFromToken(token);
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
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
}