package com.emporium.security.security.jwt;

import com.emporium.lib.security.Status;
import com.emporium.lib.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtUser implements UserDetails {

    private final String id;
    private final String username;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Status status;
    private final boolean enabled;
    private final LocalDate lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public static JwtUser newInstance(User user) {
        return new JwtUser(
                user.getId(), user.getUsername(), user.getName(), user.getSurname(), user.getEmail(),
                user.getPassword(), user.getStatus(), user.getStatus().equals(Status.ACTIVE), user.getUpdated(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isNotBlocked();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return isNotBlocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return isNotBlocked();
    }

    private boolean isNotBlocked() {
        return !status.equals(Status.BLOCKED);
    }
}
