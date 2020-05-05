package com.emporium.security.security.jwt;

import com.emporium.lib.security.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long expirationTime;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration-time}") long expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    public String createToken(String username, Role role) {
        return null;
    }

    public Authentication getAuthentication(String token) {
        return null;
    }

    public String getUsername(String token) {
        return null;
    }

    public boolean validateToken(String token) {
        return true;
    }

    private List<String> getRoleNames(List<Role> userRoles) {
        return userRoles.stream().map(Role::getName).collect(Collectors.toList());
    }
}