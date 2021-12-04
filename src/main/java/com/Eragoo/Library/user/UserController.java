package com.Eragoo.Library.user;

import com.Eragoo.Library.user.dto.UserInputDto;
import com.Eragoo.Library.user.dto.UserOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserOutputDto> register(@RequestBody @Valid UserInputDto command) {
        UserOutputDto dto = userService.register(command);
        return ResponseEntity.ok(dto);
    }
}
