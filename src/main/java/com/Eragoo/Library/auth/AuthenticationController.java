package com.Eragoo.Library.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Token> getToken(@RequestBody UserAuthenticationCommand authenticationCommand) {
        Token token = authenticationService.getToken(authenticationCommand);
        return ResponseEntity.ok(token);
    }
}
