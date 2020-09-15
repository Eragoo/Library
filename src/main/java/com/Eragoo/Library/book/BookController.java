package com.Eragoo.Library.book;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.Eragoo.Library.user.RoleValue).ADMIN)")
    public ResponseEntity<BookDto> createOrAddIfExist(@RequestBody @Valid BookCommand command) {
        BookDto dto = bookService.createOrAddIfExist(command);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}/lease")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.RoleValue).USER," +
            "T(com.Eragoo.Library.user.RoleValue).ADMIN)")
    public ResponseEntity<BookDto> lease(@PathVariable long id) {
        BookDto dto = bookService.lease(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}/return")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.RoleValue).USER," +
            "T(com.Eragoo.Library.user.RoleValue).ADMIN)")
    public ResponseEntity<BookDto> returnBook(@PathVariable long id) {
        BookDto dto = bookService.returnBook(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.RoleValue).USER," +
            "T(com.Eragoo.Library.user.RoleValue).ADMIN)")
    public ResponseEntity<List<BookDto>> getAll(BookFilteringCommand filteringCommand) {
        List<BookDto> all = bookService.getAll(filteringCommand);
        return ResponseEntity.ok(all);
    }
}
