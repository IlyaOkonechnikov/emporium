package com.emporium.auth.service;

import com.emporium.auth.model.jpa.User;
import com.emporium.auth.model.mapper.UserMapper;
import com.emporium.auth.repository.UserRepository;
import com.emporium.lib.auth.UserBasicDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final String personalAreaUrl;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
                           PasswordEncoder passwordEncoder, RestTemplate restTemplate,
                           @Value("${personal-area.host}") String personalAreaHost,
                           @Value("${personal-area.port}") String personalAreaPort,
                           @Value("${personal-area.path}") String personalAreaPath) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
        this.personalAreaUrl = String.format("http://%s:%s/%s", personalAreaHost, personalAreaPort, personalAreaPath);
    }

    @Override
    public String create(UserBasicDTO dto) {
        log.debug("create() - start. dto: {}", dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userMapper.toEntity(dto);
        String id = userRepository.insert(user).getId();
        restTemplate.postForEntity(personalAreaUrl, dto, String.class);
        log.debug("create() - end. id: {}", id);
        return id;
    }
}
