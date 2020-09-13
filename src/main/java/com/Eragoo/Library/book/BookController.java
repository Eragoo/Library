package com.Eragoo.Library.book;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> createOrAddIfExist(@RequestBody @Valid BookCommand command) {
        BookDto dto = bookService.createOrAddIfExist(command);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}/lease")
    public ResponseEntity<BookDto> lease(@PathVariable long id) {
        BookDto dto = bookService.lease(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}/return")
    public ResponseEntity<BookDto> returnBook(@PathVariable long id) {
        BookDto dto = bookService.returnBook(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BookDto>> getAll(BookFilteringCommand filteringCommand) {
        List<BookDto> all = bookService.getAll(filteringCommand);
        return ResponseEntity.ok(all);
    }
}
