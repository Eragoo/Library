package com.Eragoo.Library.book;

import com.Eragoo.Library.book.dto.BookInputDto;
import com.Eragoo.Library.book.dto.BookOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<BookOutputDto> createOrAddIfExist(@RequestBody @Valid BookInputDto command) {
        BookOutputDto dto = bookService.createOrAddIfExist(command);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}/lease")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.role.RoleValue).USER," +
            "T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<BookOutputDto> lease(@PathVariable long id) {
        BookOutputDto dto = bookService.lease(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{id}/return")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.role.RoleValue).USER," +
            "T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<BookOutputDto> returnBook(@PathVariable long id) {
        BookOutputDto dto = bookService.returnBook(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.role.RoleValue).USER," +
            "T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<List<BookOutputDto>> getAll(BookFilter filteringCommand) {
        List<BookOutputDto> all = bookService.getAll(filteringCommand);
        return ResponseEntity.ok(all);
    }
}
