package com.Eragoo.Library.genre.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GenreInputDto {
    @NotBlank(message = "Name must be not blank")
    private String name;
}
