package com.Eragoo.Library.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserAuthenticationCommand {
    @NotBlank(message = "Login must be not blank")
    private String login;
    @NotBlank(message = "Password must be not blank")
    private String password;
}
