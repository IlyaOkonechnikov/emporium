package com.emporium.auth.controller;

import com.emporium.auth.service.UserService;
import com.emporium.lib.auth.UserBasicDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid UserBasicDTO userBasicDTO) {
        return userService.create(userBasicDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserBasicDTO userBasicDTO) {
        return null;
    }
}
