package com.Eragoo.Library.auth;

import com.Eragoo.Library.error.exception.NotFoundException;
import com.Eragoo.Library.security.TokenProvider;
import com.Eragoo.Library.user.User;
import com.Eragoo.Library.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenProvider tokenProvider;


    public Token getToken(@NotNull UserAuthenticationCommand command) {
        User user = userRepository.findByLogin(command.getLogin());
        verifyUsernameAndPassword(command, user);
        String providedToken = tokenProvider.createToken(user.getLogin(), user.getRole());
        return new Token(providedToken);
    }

    private void verifyUsernameAndPassword(UserAuthenticationCommand expectedUser, User receivedUser) {
        if (receivedUser == null || !isPasswordMatches(expectedUser.getPassword(), receivedUser.getPassword())) {
            throw new NotFoundException("User with that username and password not found");
        }
    }

    private boolean isPasswordMatches(String expectedEncodedPassword, String receivedRawPassword) {
        assert expectedEncodedPassword != null && receivedRawPassword != null;
        return bCryptPasswordEncoder.matches(expectedEncodedPassword, receivedRawPassword);
    }
}
