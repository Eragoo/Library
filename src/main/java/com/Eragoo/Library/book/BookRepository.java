package com.Eragoo.Library.book;

import com.Eragoo.Library.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByNameAndAuthorIdAndPublicationDateAndGenresIn(String name,
                                                                  Long author,
                                                                  LocalDate pubDate,
                                                                  Set<Genre> genres);
}
