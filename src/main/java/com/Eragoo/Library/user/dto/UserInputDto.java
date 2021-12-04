package com.Eragoo.Library.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInputDto {
    @NotBlank(message = "Username must be not blank")
    private String username;
    @NotBlank(message = "Password must be not blank")
    private String password;
}
