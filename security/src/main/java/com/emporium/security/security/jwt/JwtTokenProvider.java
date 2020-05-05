package com.emporium.security.security.jwt;

import com.emporium.lib.security.Role;
import com.emporium.security.exception.SecurityErrorCode;
import com.emporium.security.exception.SecurityException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long expirationTime;
    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration-time}") long expirationTime,
                            @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.expirationTime = expirationTime;
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, Role role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        return (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer_")) ?
                bearerToken.substring(7) : null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new SecurityException(SecurityErrorCode.JWT_AUTHENTICATION_ERROR);
        }
    }
}