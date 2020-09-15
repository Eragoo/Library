package com.Eragoo.Library.genre;

import com.Eragoo.Library.error.exception.ConflictException;
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
        checkExistingGenreByName(command.getName());
        Genre genre = genreMapper.commandToEntity(command);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.entityToDto(savedGenre);
    }

    private void checkExistingGenreByName(String name) {
        genreRepository.findByName(name)
                .ifPresent((genre) -> {
                    throw new ConflictException("Genre with that name already exist");
                });
    }

    public List<GenreDto> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
