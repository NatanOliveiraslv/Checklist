package com.br.checklist.checklist.dto.user;

public record AuthenticationRequestDTO(
        String username,
        String password
) {
}
