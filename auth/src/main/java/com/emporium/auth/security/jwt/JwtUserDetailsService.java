package com.emporium.auth.security.jwt;

import com.emporium.lib.auth.AuthUser;
import com.emporium.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.debug("loadUserByUsername() - start. username: {}", username);
        User user = userService.findByUsername(username);
        AuthUser authUser = AuthUser.newInstance(user);
        log.debug("loadUserByUsername() - end. id: {}", user.getId());
        return authUser;
    }
}