package com.Eragoo.Library.genre;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<GenreDto> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
