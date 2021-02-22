package com.emporium.lib.auth.config;

import com.emporium.lib.auth.data.jpa.User;
import com.emporium.lib.auth.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String username) {
    User user = null;
    return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
  }
}
