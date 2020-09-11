package com.Eragoo.Library.genre;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
