package com.Eragoo.Library.author;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorCommand {
    @NotBlank(message = "Name must be not blank")
    private String name;
}
