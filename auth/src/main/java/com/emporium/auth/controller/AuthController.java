package com.emporium.auth.controller;

import com.emporium.auth.service.UserService;
import com.emporium.lib.auth.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
    public String register(@RequestBody @Valid RegistrationDTO dto) {
        return userService.create(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid RegistrationDTO dto) {
        return null;
    }

    @GetMapping("/mail-confirmation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void mailConfirmation(@PathVariable ObjectId id){
        userService.mailConfirm(id);
    }

}