package com.Eragoo.Library.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.Eragoo.Library.security.JwtProperties.AUTH_TOKEN_PREFIX;

@Component
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtProperties.AUTH_HEADER_STRING);

        if (header != null && header.startsWith(AUTH_TOKEN_PREFIX)) {
            String token = header.substring(AUTH_TOKEN_PREFIX.length());
            tokenProvider.parseUser(token)
                    .map(user -> new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()))
                    .ifPresent(auth-> SecurityContextHolder.getContext().setAuthentication(auth));
        }
        filterChain.doFilter(request, response);
    }
}
