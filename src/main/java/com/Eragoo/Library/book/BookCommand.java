package com.Eragoo.Library.book;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
public class BookCommand {
    private String name;
    private String author;
    private LocalDate publicationDate;
    private Integer amount;
    private Set<Long> genreIds;
}
