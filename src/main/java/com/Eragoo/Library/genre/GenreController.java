package com.Eragoo.Library.genre;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/genre")
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;
    @PostMapping
    public ResponseEntity<GenreDto> create(@RequestBody GenreCommand command) {
        GenreDto genreDto = genreService.create(command);
        return ResponseEntity.ok(genreDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GenreDto>> getAll() {
        List<GenreDto> all = genreService.getAll();
        return ResponseEntity.ok(all);
    }
}
