package com.emporium.auth.service;

import com.emporium.lib.auth.UserDTO;
import com.emporium.lib.auth.data.dto.LoginResponseDTO;
import com.emporium.lib.auth.data.jpa.User;

public interface UserService {

  User create(UserDTO dto);

  void enable(long id);

  LoginResponseDTO login(UserDTO dto);
}
