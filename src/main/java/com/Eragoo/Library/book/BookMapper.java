package com.Eragoo.Library.book;

import com.Eragoo.Library.genre.GenreMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = GenreMapper.class)
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Book commandToEntity(BookCommand command);

    BookDto entityToDto(Book book);
}
