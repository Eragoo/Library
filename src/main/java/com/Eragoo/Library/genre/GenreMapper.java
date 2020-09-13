package com.Eragoo.Library.genre;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GenreMapper {
    GenreDto entityToDto(Genre genre);
    @Mapping(target = "id", ignore = true)
    Genre commandToEntity(GenreCommand command);
}
