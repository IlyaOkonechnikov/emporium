package com.emporium.area.service;

import com.emporium.lib.auth.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    String create(User user);

    void update(User user);

    void delete(String id);
}
