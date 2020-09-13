package com.Eragoo.Library.book;

import com.Eragoo.Library.author.Author;
import com.Eragoo.Library.author.AuthorRepository;
import com.Eragoo.Library.error.exception.ConflictException;
import com.Eragoo.Library.error.exception.NotFoundException;
import com.Eragoo.Library.genre.Genre;
import com.Eragoo.Library.genre.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;

    public BookDto createOrAddIfExist(BookCommand bookCommand) {
        Author author = findAuthor(bookCommand.getAuthorId());

        Set<Genre> bookGenres = genreRepository.findAllByIdIn(bookCommand.getGenreIds());
        Optional<Book> existedBook = findEqualBook(bookCommand, bookGenres);

        Book book;
        if (existedBook.isPresent()) {
            book = existedBook.get();
            book.setAmount(book.getAmount() + bookCommand.getAmount());
        } else {
            book = bookMapper.commandToEntity(bookCommand);
            book.setGenres(bookGenres);
            book.setAuthor(author);
        }
        Book savedBook = bookRepository.save(book);
        return bookMapper.entityToDto(savedBook);
    }

    private Optional<Book> findEqualBook(BookCommand bookCommand, Set<Genre> bookGenres) {
        return bookRepository.findByNameAndAuthorIdAndPublicationYearAndGenresIn(bookCommand.getName(),
                bookCommand.getAuthorId(),
                bookCommand.getPublicationYear(),
                bookGenres);
    }

    private Author findAuthor(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ConflictException("Author with id " + id + " not found"));
    }

    @Transactional
    public BookDto returnBook(long id) {
        Book book = findBook(id);
        book.setAmount(book.getAmount() + 1);
        return bookMapper.entityToDto(book);
    }

    @Transactional
    public BookDto lease(long id) {
        Book book = findBook(id);
        if (book.getAmount() < 0) {
            throw new ConflictException("Can't lease this book due to it's amount");
        }
        book.setAmount(book.getAmount() - 1);

        return bookMapper.entityToDto(book);
    }

    public List<BookDto> getAll(BookFilteringCommand command) {
        Specification<Book> specification = command.getSpecification();
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    private Book findBook(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
    }
}
