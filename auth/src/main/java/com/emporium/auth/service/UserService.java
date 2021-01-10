package com.emporium.auth.service;

import com.emporium.lib.auth.UserDTO;
import com.emporium.lib.auth.data.User;

public interface UserService {

  String create(UserDTO dto);

  void enable(long id);

  User findByUsernameOrEmailAndValidatePassword(String username, String email, String password);
}
