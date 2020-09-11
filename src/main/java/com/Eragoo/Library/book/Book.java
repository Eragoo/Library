package com.Eragoo.Library.book;

import com.Eragoo.Library.genre.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private Date publicationDate;
    private int amount;

    @ManyToMany
    @JoinTable(name = "book_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres;
}
