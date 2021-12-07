package com.Eragoo.Library.genre;

import com.Eragoo.Library.genre.dto.GenreInputDto;
import com.Eragoo.Library.genre.dto.GenreOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/genres")
@AllArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    @PreAuthorize("hasAuthority(T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<GenreOutputDto> create(@RequestBody @Valid GenreInputDto command) {
        GenreOutputDto genreOutputDto = genreService.create(command);
        return ResponseEntity.ok(genreOutputDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority(T(com.Eragoo.Library.user.role.RoleValue).USER," +
            "T(com.Eragoo.Library.user.role.RoleValue).ADMIN)")
    public ResponseEntity<List<GenreOutputDto>> getAll() {
        List<GenreOutputDto> all = genreService.getAll();
        return ResponseEntity.ok(all);
    }
}
