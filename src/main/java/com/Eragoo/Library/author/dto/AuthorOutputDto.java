package com.Eragoo.Library.author.dto;

import com.Eragoo.Library.author.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AuthorOutputDto {
    private final Long id;
    private final String name;

    public AuthorOutputDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}
