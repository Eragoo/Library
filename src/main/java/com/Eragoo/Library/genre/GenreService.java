package com.Eragoo.Library.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;
    private GenreMapper genreMapper;

    public GenreDto create(GenreCommand command) {
        Genre genre = genreMapper.commandToEntity(command);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.entityToDto(savedGenre);
    }
}
