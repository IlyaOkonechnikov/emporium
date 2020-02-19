package com.emporium.area.service;

import com.emporium.area.model.jpa.User;

public interface UserService {

  User findById(long id);

  long create(User user);

  void update(User user);
}
