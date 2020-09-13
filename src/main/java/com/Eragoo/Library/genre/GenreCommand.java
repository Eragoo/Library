package com.Eragoo.Library.genre;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GenreCommand {
    @NotBlank(message = "Name must be not blank")
    private String name;
}
