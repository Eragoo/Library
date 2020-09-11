package com.Eragoo.Library.book;

import com.Eragoo.Library.genre.Genre;
import com.Eragoo.Library.genre.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
