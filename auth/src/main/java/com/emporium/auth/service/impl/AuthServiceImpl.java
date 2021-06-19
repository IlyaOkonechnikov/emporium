package com.emporium.auth.service.impl;

import com.emporium.auth.exception.AuthException;
import com.emporium.auth.exception.AuthExceptionReason;
import com.emporium.auth.service.AuthService;
import com.emporium.lib.auth.configuration.jwt.JwtProvider;
import com.emporium.lib.auth.data.dto.LoginResponseDTO;
import com.emporium.lib.auth.data.dto.UserDTO;
import com.emporium.lib.auth.data.jpa.User;
import com.emporium.lib.auth.data.mapper.UserMapper;
import com.emporium.lib.auth.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

  private final UserMapper userMapper;
  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  //  private final WebClient webClient;
  private final String accountRegistrationUrl;

  public AuthServiceImpl(
      UserMapper userMapper,
      JwtProvider jwtProvider,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      //                         WebClient webClient,
      @Value("${services.account.registration-url}") String accountRegistrationUrl) {
    this.userMapper = userMapper;
    this.jwtProvider = jwtProvider;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    //    this.webClient = webClient;
    this.accountRegistrationUrl = accountRegistrationUrl;
  }

  @Override
  //  todo check if exists
  public User create(UserDTO dto) {
    User user = userMapper.toEntity(dto);
    user.setRole("ROLE_USER");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public LoginResponseDTO login(UserDTO dto) {
    User user = validateCredentialsAndGetUser(dto.getUsername(), dto.getEmail());
    if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      return LoginResponseDTO.of(jwtProvider.generateToken(user.getUsername()));
    }
    throw new AuthException(AuthExceptionReason.INVALID_PASSWORD);
  }

  @Override
  public void enable(long id) {}

  @Override
  public void logout(HttpServletRequest request) {
    jwtProvider.invalidateToken(request);
  }

  private User validateCredentialsAndGetUser(String username, String email) {
    if (username == null && email == null) {
      throw new AuthException(AuthExceptionReason.EMAIL_AND_PASSWORD_ARE_NULL);
    } else if (username != null) {
      return userRepository
          .findByUsername(username)
          .orElseThrow(() -> new AuthException(AuthExceptionReason.NON_EXISTENT_USERNAME));
    }
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new AuthException(AuthExceptionReason.NON_EXISTENT_EMAIL));
  }

  //  public String registerAccount(UserDTO dto) {
  //    return webClient.post().uri(accountRegistrationUrl)
  //        .contentType(MediaType.APPLICATION_JSON)
  //        .bodyValue(dto)
  //        .retrieve()
  //        .bodyToMono(String.class)
  //        .block();
  //  }

  //    public UserServiceImpl(@Value("${spring.security.oauth2.resource.account-service}") String
  // accountServiceUrl,
  //                           WebClient webClient,
  //                           UserMapper userMapper,
  //                           UserRepository userRepository,
  //                           PasswordEncoder passwordEncoder,
  //                           EmailSenderService emailSenderService) {
  //        this.registrationUrl = registrationUrl;
  //        this.webClient = webClient;
  //        this.userMapper = userMapper;
  //        this.userRepository = userRepository;
  //        this.passwordEncoder = passwordEncoder;
  //        this.emailSenderService = emailSenderService;
  //    }

  //    private final String registrationUrl;
  //    private final WebClient webClient;
  //    private final UserMapper userMapper;
  //    private final UserRepository userRepository;
  //    private final PasswordEncoder passwordEncoder;
  //    private final EmailSenderService emailSenderService;
  //
  //    public UserServiceImpl(@Value("${url.personal-area-registration}") String registrationUrl,
  //                           WebClient webClient,
  //                           UserMapper userMapper,
  //                           UserRepository userRepository,
  //                           PasswordEncoder passwordEncoder,
  //                           EmailSenderService emailSenderService) {
  //        this.registrationUrl = registrationUrl;
  //        this.webClient = webClient;
  //        this.userMapper = userMapper;
  //        this.userRepository = userRepository;
  //        this.passwordEncoder = passwordEncoder;
  //        this.emailSenderService = emailSenderService;
  //    }
  //
  //    @Override
  //    public String create(UserDTO dto) {
  //        log.debug("create() - start. dto: {}", dto);
  //        String authenticPassword = dto.getPassword();
  //        dto.setPassword(passwordEncoder.encode(authenticPassword));
  //        User user = userMapper.toEntity(dto);
  //        dto.setId(userRepository.insert(user).getId());
  //        try {
  //            emailSenderService.sendConfirmationEmail(dto.getId(), dto.getUsername(),
  // dto.getEmail());
  //            String registeredAccountId = registerAccount(dto);
  //            log.debug("create() - end. registeredAccountId: {}", registeredAccountId);
  //            return registeredAccountId;
  //        } catch (Exception e) {
  //            log.error("Failed to create account. id: " + dto.getId() + "\n" + e.getMessage(),
  // e);
  //            userRepository.deleteById(dto.getId().toString());
  //            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),
  // e);
  //        }
  //    }
  //
  //    public void enable(ObjectId id) {
  //        log.debug("enable() - start. id: {}", id.toString());
  //        User user = userRepository.findById(id)
  //                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
  // "User not found."));
  //        user.setEnabled(true);
  //        log.debug("enable() - end. enabled: {}", user.isEnabled());
  //        userRepository.save(user);
  //    }
  //
}
