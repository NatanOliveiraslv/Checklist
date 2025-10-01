package com.br.checklist.checklist.dto.user;

public record AuthenticationResponseDTO(
        String accessToken,
        String refreshToken
) {
}
