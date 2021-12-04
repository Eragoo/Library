package com.Eragoo.Library.book;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static com.Eragoo.Library.book.BookSpecification.*;

@Getter
@Setter
public class BookFilter {
    private String name;
    private List<Long> authorIds;
    private Integer publicationYear;
    private Integer amount;
    private List<Long> genreIds;

    Specification<Book> toSpecification() {
        return getFilteredByName(name)
                .and(getFilteredByAuthors(authorIds))
                .and(getFilteredByGenres(genreIds))
                .and(getFilteredByYear(publicationYear))
                .and(getFilteredByAmount(amount));
    }
}
