package com.Eragoo.Library.book.dto;

import com.Eragoo.Library.author.AuthorDto;
import com.Eragoo.Library.genre.dto.GenreOutputDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookOutputDto {
    private long id;
    private String name;
    private AuthorDto author;
    private Integer publicationYear;
    private int amount;
    private Set<GenreOutputDto> genres;
}
