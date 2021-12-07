package com.Eragoo.Library.author;

import com.Eragoo.Library.author.dto.AuthorInputDto;
import com.Eragoo.Library.author.dto.AuthorOutputDto;
import com.Eragoo.Library.error.exception.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorOutputDto create(AuthorInputDto authorCommand) {
        checkExistingAuthorByName(authorCommand.getName());

        Author author = new Author(authorCommand);
        Author saved = authorRepository.save(author);
        return  new AuthorOutputDto(saved);
    }

    private void checkExistingAuthorByName(String name) {
        authorRepository.findByName(name)
                .ifPresent((author) -> {
                    throw new ConflictException("Author with that name already exist");
                });
    }

    public List<AuthorOutputDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorOutputDto::new)
                .collect(Collectors.toList());
    }
}
