package com.emporium.auth.controller;

import com.emporium.auth.service.UserService;
import com.emporium.lib.auth.UserDTO;
import com.emporium.lib.auth.config.jwt.JwtProvider;
import com.emporium.lib.auth.data.dto.LoginResponseDTO;
import com.emporium.lib.auth.data.jpa.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final JwtProvider jwtProvider;

  @GetMapping("/test")
  @ResponseStatus(HttpStatus.CREATED)
  public void test() {
    System.out.println("test");
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public User register(@RequestBody UserDTO dto) {
    return userService.create(dto);
  }

  @PostMapping("/login")
  public LoginResponseDTO login(@RequestBody @Valid UserDTO dto) {
    User user = userService.findByUsernameOrEmailAndValidatePassword(dto.getUsername(), dto.getEmail(), dto.getPassword());
    return LoginResponseDTO.of(jwtProvider.generateToken(user.getUsername()));
  }

  @GetMapping("/logout")
  public void logout(HttpServletRequest request) {

  }

  @GetMapping("/confirm-email/{id}")
  public void confirmEmail(@PathVariable long id) {
    userService.enable(id);
  }
}
