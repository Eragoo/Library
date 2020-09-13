package com.Eragoo.Library.author;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorCommand command) {
        AuthorDto dto = authorService.create(command);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> all = authorService.getAll();
        return ResponseEntity.ok(all);
    }
}
