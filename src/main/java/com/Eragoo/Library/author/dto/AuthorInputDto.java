package com.Eragoo.Library.author.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorInputDto {
    @NotBlank(message = "Name must be not blank")
    private String name;
}
