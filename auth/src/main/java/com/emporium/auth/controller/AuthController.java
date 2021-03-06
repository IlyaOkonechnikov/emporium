package com.emporium.auth.controller;

import com.emporium.auth.service.AuthService;
import com.emporium.lib.auth.data.dto.LoginResponseDTO;
import com.emporium.lib.auth.data.dto.UserDTO;
import com.emporium.lib.auth.data.jpa.User;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public User register(@RequestBody UserDTO dto) {
    return authService.create(dto);
  }

  @PostMapping("/login")
  public LoginResponseDTO login(@RequestBody @Valid UserDTO dto) {
    return authService.login(dto);
  }

  @GetMapping("/logout")
  public void logout(HttpServletRequest request) {
    authService.logout(request);
  }

  @GetMapping("/confirm-email/{username}")
  public void confirmEmail(@PathVariable String username) {
    authService.confirmEmail(username);
  }
}
