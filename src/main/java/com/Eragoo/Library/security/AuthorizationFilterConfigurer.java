package com.Eragoo.Library.security;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthorizationFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private AuthorizationFilter filter;

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
