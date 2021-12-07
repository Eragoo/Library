package com.Eragoo.Library.book.dto;

import com.Eragoo.Library.author.dto.AuthorOutputDto;
import com.Eragoo.Library.book.Book;
import com.Eragoo.Library.genre.dto.GenreOutputDto;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BookOutputDto {
    private final long id;
    private final String name;
    private final AuthorOutputDto author;
    private final Integer publicationYear;
    private final int amount;
    private final Set<GenreOutputDto> genres;

    public BookOutputDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = new AuthorOutputDto(book.getAuthor());
        this.publicationYear = book.getPublicationYear();
        this.amount = book.getAmount();
        this.genres = book.getGenres().stream()
                .map(GenreOutputDto::new)
                .collect(Collectors.toSet());
    }
}
