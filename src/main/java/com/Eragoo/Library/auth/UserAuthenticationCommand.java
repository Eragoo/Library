package com.Eragoo.Library.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationCommand {
    private String login;
    private String password;
}
