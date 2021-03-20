package com.emporium.auth.service;

import com.emporium.lib.auth.data.dto.UserDTO;
import com.emporium.lib.auth.data.dto.LoginResponseDTO;
import com.emporium.lib.auth.data.jpa.User;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

  User create(UserDTO dto);

  LoginResponseDTO login(UserDTO dto);

  void enable(long id);

  void logout(HttpServletRequest request);
}
