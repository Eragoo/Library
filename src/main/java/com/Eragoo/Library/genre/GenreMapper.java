package com.Eragoo.Library.genre;

import com.Eragoo.Library.genre.dto.GenreInputDto;
import com.Eragoo.Library.genre.dto.GenreOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GenreMapper {
    GenreOutputDto entityToDto(Genre genre);
    @Mapping(target = "id", ignore = true)
    Genre commandToEntity(GenreInputDto command);
}
