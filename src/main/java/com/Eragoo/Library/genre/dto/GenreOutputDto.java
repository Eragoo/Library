package com.Eragoo.Library.genre.dto;

import com.Eragoo.Library.genre.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GenreOutputDto {
    private final long id;
    private final String name;

    public GenreOutputDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
