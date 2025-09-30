package com.br.checklist.checklist.dto.user;

public record RegisterUserDto(
        String firstName,
        String email,
        String password
) {
}
