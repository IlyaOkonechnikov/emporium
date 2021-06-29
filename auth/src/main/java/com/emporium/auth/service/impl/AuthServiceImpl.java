package com.emporium.auth.service.impl;

import com.emporium.auth.exception.AuthException;
import com.emporium.auth.exception.AuthExceptionReason;
import com.emporium.auth.service.AuthService;
import com.emporium.auth.service.EmailSenderService;
import com.emporium.lib.auth.configuration.jwt.JwtProvider;
import com.emporium.lib.auth.data.dto.LoginResponseDTO;
import com.emporium.lib.auth.data.dto.UserDTO;
import com.emporium.lib.auth.data.jpa.User;
import com.emporium.lib.auth.data.mapper.UserMapper;
import com.emporium.lib.auth.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  @Value("${services.account.registration-url}")
  private final String accountRegistrationUrl;

  private final WebClient webClient;
  private final UserMapper userMapper;
  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final EmailSenderService emailSenderService;

  @Override
  public User create(UserDTO dto) {
    checkUsernameAndEmail(dto);
    User user = userMapper.toEntity(dto);
    user.setRole("ROLE_USER");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    try {
      emailSenderService.sendConfirmationEmail(dto.getUsername(), dto.getEmail());
      Long createdAccountId = createAccount(dto);
      log.debug("Created account with id: {}", createdAccountId);
      return userRepository.save(user);
    } catch (Exception e) {
      log.error("Unable to create account with username: {}", dto.getUsername(), e);
      throw new AuthException(AuthExceptionReason.ACCOUNT_CREATION_ERROR);
    }
  }

  public LoginResponseDTO login(UserDTO dto) {
    User user = validateCredentialsAndGetUser(dto.getUsername(), dto.getEmail());
    if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      return LoginResponseDTO.of(jwtProvider.generateToken(user.getUsername()));
    }
    throw new AuthException(AuthExceptionReason.INVALID_PASSWORD);
  }

  @Override
  public void confirmEmail(String username) {}

  @Override
  public void logout(HttpServletRequest request) {
    jwtProvider.invalidateToken(request);
  }

  private User validateCredentialsAndGetUser(String username, String email) {
    if (ObjectUtils.allNull(username, email)) {
      throw new AuthException(AuthExceptionReason.EMAIL_AND_USERNAME_ARE_NULL);
    } else if (ObjectUtils.allNotNull(username, email)) {
      throw new AuthException(AuthExceptionReason.ONLY_EMAIL_OR_USERNAME_SHOULD_BE_FILLED);
    } else if (username != null) {
      return userRepository
          .findByUsername(username)
          .orElseThrow(() -> new AuthException(AuthExceptionReason.NON_EXISTENT_USERNAME));
    }
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new AuthException(AuthExceptionReason.NON_EXISTENT_EMAIL));
  }

  private void checkUsernameAndEmail(UserDTO dto) {
    if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
      throw new AuthException(AuthExceptionReason.DUPLICATE_USERNAME);
    }
    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new AuthException(AuthExceptionReason.DUPLICATE_EMAIL);
    }
  }

  private Long createAccount(UserDTO dto) {
    return webClient
        .post()
        .uri(accountRegistrationUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .retrieve()
        .bodyToMono(Long.class)
        .block();
  }
}
