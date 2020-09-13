package com.Eragoo.Library.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Set;

import static com.Eragoo.Library.book.BookSpecification.*;

@Getter
@Setter
public class BookFilteringCommand {
    private String name;
    private Set<Long> authorIds;
    private LocalDate publicationDate;
    private Integer amount;
    private Set<Long> genreIds;

    Specification<Book> getSpecification() {
        return getFilteredByName(name)
                .and(getFilteredByAuthors(authorIds))
                .and(getFilteredByGenres(genreIds));
    }
}
