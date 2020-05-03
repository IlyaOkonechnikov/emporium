package com.emporium.security.service.impl;

import com.emporium.lib.security.Role;
import com.emporium.lib.security.User;
import com.emporium.security.exception.SecurityErrorCode;
import com.emporium.security.exception.SecurityException;
import com.emporium.security.mapper.UserMapper;
import com.emporium.security.model.UserDTO;
import com.emporium.security.repository.RoleRepository;
import com.emporium.security.repository.UserRepository;
import com.emporium.security.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String ROLE_USER = "ROLE_USER";

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User signUp(UserDTO userDTO) {
    log.debug("signUp() - end. userDTO: {}", userDTO);
    if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
      log.error("An error occurred while trying to register an account with an existing email address. email: {}", userDTO.getEmail());
      throw new SecurityException(SecurityErrorCode.EMAIL_EXISTS_ERROR);
    }
    User user = userMapper.toEntity(userDTO);
    Role role = roleRepository.findByName(ROLE_USER).orElseThrow(() -> new SecurityException(SecurityErrorCode.ROLE_NOT_FOUND_ERROR));
    user.setRole(role);
    return user;
  }

  @Override
  public User findById(String id) {
    return null;
  }

  @Override
  public User findByUsername(String username) {
    return null;
  }

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public void delete(String id) {

  }
}
