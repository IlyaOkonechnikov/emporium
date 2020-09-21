package com.emporium.auth.service;

import com.emporium.auth.client.AuthClient;
import com.emporium.auth.client.PersonalAreaClient;
import com.emporium.auth.model.jpa.User;
import com.emporium.auth.model.mapper.UserMapper;
import com.emporium.auth.repository.UserRepository;
import com.emporium.lib.auth.UserBasicDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthClient authClient;
    private final PersonalAreaClient personalAreaClient;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
                           PasswordEncoder passwordEncoder, AuthClient authClient,
                           PersonalAreaClient personalAreaClient) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authClient = authClient;
        this.personalAreaClient = personalAreaClient;
    }

    @Override
    public String create(UserBasicDTO dto) {
        log.debug("create() - start. dto: {}", dto);
        String authenticPassword = dto.getPassword();
        dto.setPassword(passwordEncoder.encode(authenticPassword));
        User user = userMapper.toEntity(dto);
        String id = userRepository.insert(user).getId();
        String bearerToken = authClient.getBearerToken("password", dto.getUsername(), authenticPassword);
        personalAreaClient.register(String.format("Bearer %s", bearerToken), dto);
        log.debug("create() - end. id: {}", id);
        return id;
    }
}
