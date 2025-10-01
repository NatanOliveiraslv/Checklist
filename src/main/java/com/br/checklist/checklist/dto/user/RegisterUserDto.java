package com.br.checklist.checklist.dto.user;

import com.br.checklist.checklist.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDto(
        @NotBlank
        String userName,
        @NotBlank
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        @NotBlank
        String email
) {
    public RegisterUserDto(User user) {
        this(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}