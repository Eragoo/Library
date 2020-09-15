package com.Eragoo.Library.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class AuthenticatedUser extends User {
    public AuthenticatedUser(String username, String role) {
        super(username, "", List.of(new SimpleGrantedAuthority(role)));
    }
}
