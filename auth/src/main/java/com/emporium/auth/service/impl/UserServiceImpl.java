package com.emporium.auth.service.impl;

import com.emporium.auth.service.UserService;
import com.emporium.lib.auth.UserDTO;
import com.emporium.lib.auth.data.jpa.Role;
import com.emporium.lib.auth.data.jpa.User;
import com.emporium.lib.auth.data.mapper.UserMapper;
import com.emporium.lib.auth.repository.RoleRepository;
import com.emporium.lib.auth.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String INVALID_USERNAME_OR_EMAIL_MSG = "Invalid username or email.";
  private static final String INVALID_PASSWORD_MSG = "Invalid password.";

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
//  private final String accountServiceUrl;
//  private final WebClient webClient;



//  public UserServiceImpl(@Value("${okta.oauth2.audience}") String accountServiceUrl,
//                         WebClient webClient) {
//    this.accountServiceUrl = accountServiceUrl;
//    this.webClient = webClient;
//  }

  @Override
  public User create(UserDTO dto) {
//      return this.webClient
//          .get()
//          .uri(this.accountServiceUrl + "/account/api")
//          .retrieve()
//          .bodyToMono(String.class)
//          .block();
    Role userRole = roleRepository.findByName("ROLE_USER");
    User user = userMapper.toEntity(dto);
    user.setRole(userRole);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User findByUsernameOrEmailAndValidatePassword(String username, String email, String password) {
    User user = userRepository.findByUsernameOrEmail(username, email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INVALID_USERNAME_OR_EMAIL_MSG));
    if (passwordEncoder.matches(password, user.getPassword())) {
      return user;
    }
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INVALID_PASSWORD_MSG);
  }

  @Override
  public void enable(long id) {

  }

//    public UserServiceImpl(@Value("${spring.security.oauth2.resource.account-service}") String accountServiceUrl,
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
//            emailSenderService.sendConfirmationEmail(dto.getId(), dto.getUsername(), dto.getEmail());
//            String registeredAccountId = registerAccount(dto);
//            log.debug("create() - end. registeredAccountId: {}", registeredAccountId);
//            return registeredAccountId;
//        } catch (Exception e) {
//            log.error("Failed to create account. id: " + dto.getId() + "\n" + e.getMessage(), e);
//            userRepository.deleteById(dto.getId().toString());
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
//        }
//    }
//
//    public void enable(ObjectId id) {
//        log.debug("enable() - start. id: {}", id.toString());
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found."));
//        user.setEnabled(true);
//        log.debug("enable() - end. enabled: {}", user.isEnabled());
//        userRepository.save(user);
//    }
//
//    public String registerAccount(UserDTO dto) {
//        return webClient.post().uri(registrationUrl)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(dto)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
}