package com.Eragoo.Library.user;

import com.Eragoo.Library.user.role.RoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private RoleDto role;
}
