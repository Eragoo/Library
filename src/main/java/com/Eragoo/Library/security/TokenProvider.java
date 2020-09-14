package com.Eragoo.Library.security;

import com.Eragoo.Library.user.RoleValue;
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
    private static final String ROLE_CLAIM = "roleValue";

    private SecurityProps securityProps;

    public String createToken(String login, RoleValue roleValue) {
        return Jwts.builder()
                .claim(LOGIN_CLAIM, login)
                .claim(ROLE_CLAIM, roleValue)
                .setExpiration(Date.from(Instant.now().plus(securityProps.getLifetime())))
                .signWith(Keys.hmacShaKeyFor(securityProps.getSignature().getBytes()))
                .compact();
    }
}
