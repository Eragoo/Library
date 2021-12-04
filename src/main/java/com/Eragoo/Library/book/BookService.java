package com.Eragoo.Library.book;

import com.Eragoo.Library.author.Author;
import com.Eragoo.Library.author.AuthorRepository;
import com.Eragoo.Library.book.dto.BookInputDto;
import com.Eragoo.Library.book.dto.BookOutputDto;
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
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookOutputDto createOrAddIfExist(BookInputDto bookInputDto) {
        Author author = findAuthor(bookInputDto.getAuthorId());

        Set<Genre> bookGenres = genreRepository.findAllByIdIn(bookInputDto.getGenreIds());
        Optional<Book> equalBook = findEqualBook(bookInputDto, bookGenres);

        Book book;
        if (equalBook.isPresent()) {
            book = equalBook.get();
            book.setAmount(book.getAmount() + bookInputDto.getAmount());
        } else {
            book = bookMapper.commandToEntity(bookInputDto);
            book.setGenres(bookGenres);
            book.setAuthor(author);
        }
        Book savedBook = bookRepository.save(book);
        return bookMapper.entityToDto(savedBook);
    }

    private Optional<Book> findEqualBook(BookInputDto bookInputDto, Set<Genre> bookGenres) {
        return bookRepository.findByNameAndAuthorIdAndPublicationYearAndGenresIn(bookInputDto.getName(),
                bookInputDto.getAuthorId(),
                bookInputDto.getPublicationYear(),
                bookGenres);
    }

    private Author findAuthor(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ConflictException("Author with id " + id + " not found"));
    }

    @Transactional
    public BookOutputDto returnBook(long id) {
        Book book = findBook(id);
        book.setAmount(book.getAmount() + 1);
        return bookMapper.entityToDto(book);
    }

    @Transactional
    public BookOutputDto lease(long id) {
        Book book = findBook(id);
        if (book.getAmount() < 1) {
            throw new ConflictException("Can't lease this book due to it's amount");
        }
        book.setAmount(book.getAmount() - 1);

        return bookMapper.entityToDto(book);
    }

    public List<BookOutputDto> getAll(BookFilter command) {
        Specification<Book> specification = command.toSpecification();
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
