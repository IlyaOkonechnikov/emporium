package com.emporium.lib.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Data
@Getter
@RequiredArgsConstructor
@Document(value = "users")
public class AuthUser implements UserDetails {

    @Id
    private final String id;

    @Indexed(unique = true)
    @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
    @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
    private final String username;

    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
    private final String password;

    private final Status status;

    @DBRef
    private Role role;

    private final GrantedAuthority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
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

    @Override
    public boolean isEnabled() {
        return isNotBlocked();
    }

    private boolean isNotBlocked() {
        return !status.equals(Status.BLOCKED);
    }
}
