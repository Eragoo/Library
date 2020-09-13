package com.Eragoo.Library.book;

import com.Eragoo.Library.error.exception.ConflictException;
import com.Eragoo.Library.error.exception.NotFoundException;
import com.Eragoo.Library.genre.Genre;
import com.Eragoo.Library.genre.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private GenreRepository genreRepository;

    public BookDto createOrAddIfExist(BookCommand bookCommand) {
        Set<Genre> bookGenres = genreRepository.findAllByIdIn(bookCommand.getGenreIds());
        Optional<Book> existedBook = bookRepository.findByNameAndAuthorAndPublicationDateAndGenresIn(bookCommand.getName(),
                bookCommand.getAuthor(),
                bookCommand.getPublicationDate(),
                bookGenres);

        Book book;
        if (existedBook.isPresent()) {
            book = existedBook.get();
            book.setAmount(book.getAmount() + bookCommand.getAmount());
        } else {
            book = bookMapper.commandToEntity(bookCommand);
            book.setGenres(bookGenres);
        }
        Book savedBook = bookRepository.save(book);
        return bookMapper.entityToDto(savedBook);
    }

    @Transactional
    public BookDto returnBook(long id) {
        Book book = findBook(id);
        book.setAmount(book.getAmount() + 1);
        return bookMapper.entityToDto(book);
    }

    public BookDto lease(long id) {
        Book book = findBook(id);
        if (book.getAmount() < 0) {
            throw new ConflictException("Can't lease this book due to it's amount");
        }
        book.setAmount(book.getAmount() - 1);

        return bookMapper.entityToDto(book);
    }

    private Book findBook(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id " + id + " not found"));
    }
}
