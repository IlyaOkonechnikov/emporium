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

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User signUp(UserDTO userDTO) {
        log.debug("signUp() - start. userDTO: {}", userDTO);
        checkUsernameAndEmail(userDTO);
        User user = userMapper.createUser(userDTO);
        Role role = roleRepository.findByName(ROLE_USER).orElseThrow(() -> new SecurityException(SecurityErrorCode.ROLE_NOT_FOUND_ERROR));
        user.setRole(role);
        User savedUser = userRepository.save(user);
        log.debug("signUp() - end. saved user id: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    public User findById(String id) {
        log.debug("findById() - start. id: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            log.error("An error occurred due to the attempt to find a nonexistent user. id: {}", id);
            throw new SecurityException(SecurityErrorCode.USER_NOT_FOUND_ERROR);
        }
        User user = optionalUser.get();
        log.debug("findById() - end. user: {}", user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        log.debug("findByUsername() - start. id: {}", username);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            log.error("An error occurred due to the attempt to find a nonexistent user. username: {}", username);
            throw new SecurityException(SecurityErrorCode.USER_NOT_FOUND_ERROR);
        }
        User user = optionalUser.get();
        log.debug("findByUsername() - end. user: {}", user);
        return user;
    }

    @Override
    public List<User> findAll() {
        log.debug("findAll() - start.");
        List<User> users = userRepository.findAll();
        log.debug("findAll() - end. users count: {}", users.size());
        return users;
    }

    @Override
    public void delete(String id) {
        log.debug("delete() - start. id: {}", id);
        if (userRepository.findById(id).isEmpty()) {
            log.error("An error occurred due to the attempt to delete a nonexistent user. id: {}", id);
            throw new SecurityException(SecurityErrorCode.USER_NOT_FOUND_ERROR);
        }
        userRepository.deleteById(id);
        log.debug("delete() - end. id: {}", id);
    }

    private void checkUsernameAndEmail(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            log.error("An error occurred due to the attempt to register an account " +
                    "with an existing username. username: {}", userDTO.getUsername());
            throw new SecurityException(SecurityErrorCode.USERNAME_EXISTS_ERROR);
        }
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            log.error("An error occurred due to the attempt to register an account " +
                    "with an existing email. email: {}", userDTO.getEmail());
            throw new SecurityException(SecurityErrorCode.EMAIL_EXISTS_ERROR);
        }
    }
}
