package com.Eragoo.Library.book;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> add(@RequestBody BookCommand command) {
        BookDto dto = bookService.createOrAddIfExist(command);
        return ResponseEntity.ok(dto);
    }
}
