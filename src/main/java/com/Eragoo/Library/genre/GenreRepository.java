package com.Eragoo.Library.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
    Set<Genre> findAllByIdIn(Set<Long> ids);
}
