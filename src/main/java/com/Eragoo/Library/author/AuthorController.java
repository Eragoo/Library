package com.Eragoo.Library.author;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/author")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<AuthorDto> create(@RequestBody @Valid AuthorCommand command) {
        AuthorDto dto = authorService.create(command);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.role.RoleValue).USER," +
            "T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> all = authorService.getAll();
        return ResponseEntity.ok(all);
    }
}
