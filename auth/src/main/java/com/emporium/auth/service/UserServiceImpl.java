package com.emporium.auth.service;

import com.emporium.auth.model.mapper.UserMapper;
import com.emporium.auth.repository.UserRepository;
import com.emporium.lib.auth.UserBasicDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public String create(UserBasicDTO userBasicDTO) {
        log.debug("create() - start. user: {}", userBasicDTO);
        String id = userRepository.insert(userMapper.toEntity(userBasicDTO)).getId();
        log.debug("create() - end. id: {}", id);
        return id;
    }
}
