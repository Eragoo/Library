package com.Eragoo.Library.book;

import com.Eragoo.Library.author.AuthorDto;
import com.Eragoo.Library.genre.GenreDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class BookDto {
    private long id;
    private String name;
    private AuthorDto author;
    private LocalDate publicationDate;
    private int amount;
    private Set<GenreDto> genres;
}
