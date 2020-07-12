package com.emporium.auth.service;

import com.emporium.auth.model.mapper.UserMapper;
import com.emporium.auth.repository.UserRepository;
import com.emporium.lib.auth.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public String create(UserRegisterDTO userDTO) {
        log.debug("create() - start. user: {}", userDTO);
        String id = userRepository.insert(userMapper.toEntity(userDTO)).getId();
        log.debug("create() - end. id: {}", id);
        return id;
    }
}
