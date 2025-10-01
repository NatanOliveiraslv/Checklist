package com.br.checklist.checklist.exceptions;

public class UnauthorizedUser extends RuntimeException {

    public UnauthorizedUser() {
        super("Usuário não possui autorização");
    }

    public UnauthorizedUser(String message) {
        super(message);
    }
}
