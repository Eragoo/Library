package com.Eragoo.Library.security;

import com.Eragoo.Library.user.Role;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import io.jsonwebtoken.Jwts;

@AllArgsConstructor
@Component
public class TokenProvider {
    private static final String LOGIN_CLAIM = "login";
    private static final String ROLE_CLAIM = "role";

    private SecurityProps securityProps;

    public String createToken(String login, Role role) {
        return Jwts.builder()
                .claim(LOGIN_CLAIM, login)
                .claim(ROLE_CLAIM, role)
                .setExpiration(Date.from(Instant.now().plus(securityProps.getLifetime())))
                .signWith(Keys.hmacShaKeyFor(securityProps.getSignature().getBytes()))
                .compact();
    }
}
