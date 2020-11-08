package com.emporium.auth.controller;

import com.emporium.auth.service.UserService;
import com.emporium.lib.auth.UserDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class AuthController {

    private final TokenStore tokenStore;
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
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }

    @GetMapping("/confirm-email/{id}")
    public void confirmEmail(@PathVariable ObjectId id) {
        userService.enable(id);
    }
}
