package com.emporium.auth.service;

import com.emporium.auth.exception.AuthErrorCode;
import com.emporium.auth.exception.AuthException;
import com.emporium.auth.model.jpa.User;
import com.emporium.auth.model.mapper.UserMapper;
import com.emporium.auth.repository.UserRepository;
import com.emporium.lib.auth.UserBasicDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String create(UserBasicDTO userBasicDTO) {
        log.debug("create() - start. user: {}", userBasicDTO);
        if (userRepository.findByUsername(userBasicDTO.getUsername()).isPresent()) {
            throw new AuthException(AuthErrorCode.DUPLICATE_USERNAME_ERROR);
        }
        if (userRepository.findByEmail(userBasicDTO.getEmail()).isPresent()) {
            throw new AuthException(AuthErrorCode.DUPLICATE_EMAIL_ERROR);
        }
        User user = userMapper.toEntity(userBasicDTO);
        user.setPassword(passwordEncoder.encode(userBasicDTO.getPassword()));
        String id = userRepository.insert(user).getId();
        log.debug("create() - end. id: {}", id);
        return id;
    }
}
