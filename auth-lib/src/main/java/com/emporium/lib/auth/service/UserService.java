package com.emporium.lib.auth.service;

import com.emporium.lib.auth.data.Role;
import com.emporium.lib.auth.data.User;
import com.emporium.lib.auth.repository.RoleEntityRepository;
import com.emporium.lib.auth.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final RoleEntityRepository roleEntityRepository;
  private final PasswordEncoder passwordEncoder;

  public User saveUser(User user) {
    Role userRole = roleEntityRepository.findByName("ROLE_USER");
    user.setRole(userRole);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User findByLogin(String login) {
    return userRepository.findByLogin(login);
  }
}
