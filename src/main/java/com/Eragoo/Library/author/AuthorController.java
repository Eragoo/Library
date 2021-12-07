package com.Eragoo.Library.author;

import com.Eragoo.Library.author.dto.AuthorInputDto;
import com.Eragoo.Library.author.dto.AuthorOutputDto;
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
    private final AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<AuthorOutputDto> create(@RequestBody @Valid AuthorInputDto command) {
        AuthorOutputDto dto = authorService.create(command);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.role.RoleValue).USER," +
            "T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<List<AuthorOutputDto>> getAll() {
        List<AuthorOutputDto> all = authorService.getAll();
        return ResponseEntity.ok(all);
    }
}
