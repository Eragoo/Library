package com.Eragoo.Library.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    User commandToEntity(UserCommand userCommand);
    UserDto entityToDto(User user);
}
