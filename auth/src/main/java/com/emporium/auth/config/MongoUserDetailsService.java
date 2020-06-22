package com.emporium.auth.config;

import com.emporium.auth.repository.AuthorityRepository;
import com.emporium.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.emporium.lib.auth.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found. username: " + username));
        List<SimpleGrantedAuthority> authorities = authorityRepository.findByUsername(username).stream()
                .map(a -> new SimpleGrantedAuthority(a.getId().getAuthority()))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}