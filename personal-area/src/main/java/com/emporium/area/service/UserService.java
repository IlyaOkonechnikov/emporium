package com.emporium.area.service;

import com.emporium.area.model.User;
import com.emporium.lib.auth.UserBasicDTO;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    String create(UserBasicDTO dto);

    void update(User user);

    void delete(String id);
}
