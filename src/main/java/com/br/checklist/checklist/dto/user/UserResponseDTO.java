package com.br.checklist.checklist.dto.user;

import com.br.checklist.checklist.models.User;

import java.util.UUID;

public record UserResponseDTO (
        UUID id,
        String login,
        String firstName,
        String lastName,
        String email
) {
    public UserResponseDTO(User userEntity) {
        this(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail()
        );
    }
}