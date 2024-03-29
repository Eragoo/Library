package com.Eragoo.Library.user;

import com.Eragoo.Library.error.exception.ConflictException;
import com.Eragoo.Library.user.dto.UserInputDto;
import com.Eragoo.Library.user.dto.UserOutputDto;
import com.Eragoo.Library.user.role.Role;
import com.Eragoo.Library.user.role.RoleRepository;
import com.Eragoo.Library.user.role.RoleValue;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class UserService {
    private static final RoleValue DEFAULT_USER_ROLE = RoleValue.USER;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserOutputDto register(UserInputDto command) {
        validateUserCommand(command);
        User user = userMapper.commandToEntity(command);
        Role defaultUserRole = findDefaultUserRole();
        user.setRole(defaultUserRole);
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDto(savedUser);
    }

    private Role findDefaultUserRole() {
        return roleRepository.findByName(DEFAULT_USER_ROLE)
                .orElseThrow(()->new ConflictException("Role with name " + DEFAULT_USER_ROLE.name() + " not exist"));
    }

    private void validateUserCommand(UserInputDto command) {
        User user = userRepository.findByUsername(command.getUsername());
        if (nonNull(user)) {
            throw new ConflictException("User with this username already exist");
        }
    }
}
