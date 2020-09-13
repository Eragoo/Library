package com.Eragoo.Library.book;

import com.Eragoo.Library.author.Author_;
import com.Eragoo.Library.genre.Genre_;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

import static java.util.Objects.isNull;

public class BookSpecification {
    public static Specification<Book> getFilteredByName(String name) {
        return (r, cq, cb) -> isNull(name) ?
                null : cb.like(cb.upper(r.get(Book_.NAME)), name.toUpperCase() + "%");
    }

    public static Specification<Book> getFilteredByAuthors(Collection<Long> authorId) {
        return (r, cq, cb) -> isNull(authorId) ?
                null : r.join(Book_.AUTHOR).get(Author_.ID).in(authorId);
    }

    public static Specification<Book> getFilteredByGenres(Collection<Long> genreIds) {
        return (r, cq, cb) -> isNull(genreIds) ?
                null : r.join(Book_.GENRES).get(Genre_.ID).in(genreIds);
    }
}
