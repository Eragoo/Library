package com.Eragoo.Library.book;

import com.Eragoo.Library.author.AuthorMapper;
import com.Eragoo.Library.book.dto.BookInputDto;
import com.Eragoo.Library.book.dto.BookOutputDto;
import com.Eragoo.Library.genre.GenreMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {GenreMapper.class, AuthorMapper.class})
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "genres", ignore = true)
    Book commandToEntity(BookInputDto command);

    BookOutputDto entityToDto(Book book);
}
