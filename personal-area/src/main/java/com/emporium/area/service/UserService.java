package com.emporium.area.service;

import com.emporium.area.model.jpa.User;

public interface UserService {

  User findById(String id);

  String create(User user);

  void update(User user);

  void delete(String id);
}
