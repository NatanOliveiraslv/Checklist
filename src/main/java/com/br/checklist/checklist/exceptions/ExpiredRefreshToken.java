package com.br.checklist.checklist.exceptions;

public class ExpiredRefreshToken extends RuntimeException {
    public ExpiredRefreshToken() {
        super("Token de atualização inválido ou expirado");
    }

    public ExpiredRefreshToken(String message) {
        super(message);
    }
}
