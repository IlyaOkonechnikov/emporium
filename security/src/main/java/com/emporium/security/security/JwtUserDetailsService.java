package com.emporium.security.security;

import com.emporium.lib.security.User;
import com.emporium.security.security.jwt.JwtUser;
import com.emporium.security.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.debug("loadUserByUsername() - start. username: {}", username);
        User user = userService.findByUsername(username);
        JwtUser jwtUser = JwtUser.newInstance(user);
        log.debug("loadUserByUsername() - end. id: {}", user.getId());
        return jwtUser;
    }
}