package com.Eragoo.Library.genre;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/genre")
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.Eragoo.Library.user.RoleValue).ADMIN)")
    public ResponseEntity<GenreDto> create(@RequestBody @Valid GenreCommand command) {
        GenreDto genreDto = genreService.create(command);
        return ResponseEntity.ok(genreDto);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.RoleValue).USER," +
            "T(com.Eragoo.Library.user.RoleValue).ADMIN)")
    public ResponseEntity<List<GenreDto>> getAll() {
        List<GenreDto> all = genreService.getAll();
        return ResponseEntity.ok(all);
    }
}
