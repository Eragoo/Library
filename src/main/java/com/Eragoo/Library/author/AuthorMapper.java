package com.Eragoo.Library.author;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuthorMapper {
    AuthorDto entityToDto(Author author);
    @Mapping(target = "id", ignore = true)
    Author commandToEntity(AuthorCommand command);
}
