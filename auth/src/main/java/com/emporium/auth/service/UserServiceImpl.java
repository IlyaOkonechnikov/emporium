package com.emporium.auth.service;

import com.emporium.auth.model.jpa.User;
import com.emporium.auth.model.mapper.UserMapper;
import com.emporium.auth.repository.UserRepository;
import com.emporium.lib.auth.RegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final String registrationUrl;
    private final WebClient webClient;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailConfirmationService mailConfirmationService;

    public UserServiceImpl(@Value("${external-services.personal-area.registration-url}") String registrationUrl,
                           WebClient webClient,
                           UserMapper userMapper,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder, MailConfirmationService mailConfirmationService) {
        this.registrationUrl = registrationUrl;
        this.webClient = webClient;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailConfirmationService = mailConfirmationService;
    }

    @Override
    public String create(RegistrationDTO dto) {
        log.debug("create() - start. dto: {}", dto);
        String authenticPassword = dto.getPassword();
        dto.setPassword(passwordEncoder.encode(authenticPassword));
        User user = userMapper.toEntity(dto);
        dto.setId(userRepository.insert(user).getId());
        try {
            String registeredAccountId = registerAccount(dto);
            log.debug("create() - end. registeredAccountId: {}", registeredAccountId);
            mailConfirmationService.sendMailConfirmLetter(dto.getUsername(),dto.getEmail(),dto.getId());
            return registeredAccountId;
        } catch (Exception e) {
            log.error("Failed to create account. id: " + dto.getId() + "\n" + e.getMessage(), e);
            userRepository.deleteById(dto.getId().toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public User findByEmail(String email) {
        log.debug("findByEmail() - start. email: {}",email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
            log.error("Failed to find user by email. email: {}",email);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found.");
        }
        log.debug("findByEmail() - end. user: {}", optionalUser.get());
        return optionalUser.get();
    }

    @Override
    public User findById(ObjectId id) {
        log.debug("findById() - start. id: {}",id.toString());
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            log.error("Failed to find user by id. id: {}",id);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found.");
        }
        log.debug("findById() - end. user: {}", optionalUser.get());
        return optionalUser.get();
    }

    @Override
    public void mailConfirm(ObjectId id) {
        log.debug("mailConfirm() - start. id: {}",id.toString());
        User user = findById(id);
        user.setEnabled(true);
        log.debug("mailConfirm() - end. enable: {}", user.isEnabled());
        userRepository.save(user);
    }

    public String registerAccount(RegistrationDTO dto) {
        return webClient.post().uri(registrationUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}