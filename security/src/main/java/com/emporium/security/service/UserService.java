package com.emporium.security.service;

import com.emporium.lib.security.User;
import com.emporium.security.model.UserDTO;

import java.util.List;

public interface UserService {

    User signUp(UserDTO userDTO);

    User findById(String id);

    User findByUsername(String username);

    List<User> findAll();

    void delete(String id);
}
