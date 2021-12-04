package com.Eragoo.Library.user;

import com.Eragoo.Library.user.dto.UserInputDto;
import com.Eragoo.Library.user.dto.UserOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    User commandToEntity(UserInputDto userInputDto);
    UserOutputDto entityToDto(User user);
}
