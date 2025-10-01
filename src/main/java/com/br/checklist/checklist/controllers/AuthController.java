package com.br.checklist.checklist.controllers;

import com.br.checklist.checklist.dto.user.AuthenticationRequestDTO;
import com.br.checklist.checklist.dto.user.AuthenticationResponseDTO;
import com.br.checklist.checklist.dto.user.RegisterUserDto;
import com.br.checklist.checklist.dto.user.UserResponseDTO;
import com.br.checklist.checklist.exceptions.UnauthorizedUser;
import com.br.checklist.checklist.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid RegisterUserDto data) {
        return ResponseEntity.ok(userService.createUser(data));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO data) {
        return ResponseEntity.ok(userService.authenticate(data));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
        if (refreshToken == null) {
            throw new UnauthorizedUser("Refresh token ausente");
        }
        try {
            return ResponseEntity.ok(userService.refreshToken(refreshToken));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Refresh token inválido ou expirado.");
        }
    }

    // Sem funcionalidade no momento
    @PostMapping("/logout")
    public ResponseEntity<?> revokeToken(HttpServletResponse response) {
        return ResponseEntity.noContent().build();
    }

}
