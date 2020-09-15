package com.Eragoo.Library.security;

import com.Eragoo.Library.user.RoleValue;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import io.jsonwebtoken.Jwts;

@AllArgsConstructor
@Component
public class TokenProvider {
    private static final String LOGIN_CLAIM = "login";
    private static final String ROLE_CLAIM = "role";

    private SecurityProperties securityProperties;

    public String createToken(String login, RoleValue roleValue) {
        return Jwts.builder()
                .claim(LOGIN_CLAIM, login)
                .claim(ROLE_CLAIM, roleValue.name())
                .setExpiration(Date.from(Instant.now().plus(securityProperties.getLifetime())))
                .signWith(Keys.hmacShaKeyFor(securityProperties.getSignature().getBytes()))
                .compact();
    }

    public Optional<AuthenticatedUser> parseUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityProperties.getSignature().getBytes())
                .parseClaimsJws(token)
                .getBody();

        String role = claims.get(ROLE_CLAIM, String.class);
        String login = claims.get(LOGIN_CLAIM, String.class);
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(login, role);
        return Optional.of(authenticatedUser);
    }
}
