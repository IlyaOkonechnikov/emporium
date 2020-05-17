package com.emporium.auth.service;

import com.emporium.auth.model.UserDTO;

import java.util.List;

public interface UserService {

    User signUp(UserDTO userDTO);

    User findById(String id);

    User findByUsername(String username);

    List<User> findAll();

    void delete(String id);
}
