package com.emporium.auth.controller;

import com.emporium.auth.model.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emporium/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @PostMapping("/sign-up")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authRequest) {
        return null;
    }
}
