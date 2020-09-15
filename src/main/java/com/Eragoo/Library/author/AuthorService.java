package com.Eragoo.Library.author;

import com.Eragoo.Library.error.exception.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Transactional
    public AuthorDto create(AuthorCommand authorCommand) {
        checkExistingAuthorByName(authorCommand.getName());

        Author author = authorMapper.commandToEntity(authorCommand);
        Author saved = authorRepository.save(author);
        return authorMapper.entityToDto(saved);
    }

    private void checkExistingAuthorByName(String name) {
        authorRepository.findByName(name)
                .ifPresent((author) -> {
                    throw new ConflictException("Author with that name already exist");
                });
    }

    public List<AuthorDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
