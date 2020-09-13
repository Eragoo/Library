package com.Eragoo.Library.author;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    public AuthorDto create(AuthorCommand authorCommand) {
        Author author = authorMapper.commandToEntity(authorCommand);
        Author saved = authorRepository.save(author);
        return authorMapper.entityToDto(saved);
    }

    public List<AuthorDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
