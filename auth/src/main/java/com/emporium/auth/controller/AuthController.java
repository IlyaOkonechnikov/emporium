package com.emporium.auth.controller;

import com.emporium.auth.service.UserService;
import com.emporium.lib.auth.UserDTO;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public String register(@RequestBody @Valid UserDTO dto) {
    return userService.create(dto);
  }

  @PostMapping("/login")
  public String login(@RequestBody @Valid UserDTO dto) {
    return null;
  }

  @GetMapping("/logout")
  public void logout(HttpServletRequest request) {

  }

  @GetMapping("/confirm-email/{id}")
  public void confirmEmail(@PathVariable ObjectId id) {
    userService.enable(id);
  }
}
