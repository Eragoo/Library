package com.Eragoo.Library.book;

import com.Eragoo.Library.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Optional<Book> findByNameAndAuthorIdAndPublicationYearAndGenresIn(
            String name,
            Long author,
            Integer year,
            Collection<Genre> genres
    );
}

