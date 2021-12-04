package com.Eragoo.Library.genre;

import com.Eragoo.Library.error.exception.ConflictException;
import com.Eragoo.Library.genre.dto.GenreInputDto;
import com.Eragoo.Library.genre.dto.GenreOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Transactional
    public GenreOutputDto create(GenreInputDto command) {
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

    @Transactional(readOnly = true)
    public List<GenreOutputDto> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
