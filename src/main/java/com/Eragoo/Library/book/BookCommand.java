package com.Eragoo.Library.book;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class BookCommand {
    private String name;
    private Long authorId;
    private Integer publicationYear;
    private Integer amount;
    private Set<Long> genreIds;
}
