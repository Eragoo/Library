package com.Eragoo.Library.book;

import com.Eragoo.Library.author.Author;
import com.Eragoo.Library.book.dto.BookInputDto;
import com.Eragoo.Library.genre.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Author author;

    private Integer publicationYear;

    private int amount;

    @ManyToMany
    @JoinTable(name = "book_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres;

    public Book(BookInputDto command, Author author, Set<Genre> genres) {
        this.name = command.getName();
        this.author = author;
        this.publicationYear = command.getPublicationYear();
        this.amount = command.getAmount();
        this.genres = genres;
    }

    public void lease() {
        if (amount < 1) {
            throw new RuntimeException("There is no books for lease with name: " + name);
        }
        this.amount -= 1;
    }

    public void returnBook() {
        this.amount += 1;
    }

    public Set<Genre> getGenres() {
        return Optional.ofNullable(genres).orElseGet(HashSet::new);
    }
}
