package com.Eragoo.Library.user.dto;

import com.Eragoo.Library.user.role.RoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutputDto {
    private Long id;
    private String username;
    private RoleDto role;
}
