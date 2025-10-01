package com.br.checklist.checklist.exceptions;

public class ObjectNotFound extends RuntimeException {
    public ObjectNotFound() {
        super("Id não encontrado");
    }

    public ObjectNotFound(String mensagem) {
        super(mensagem);
    }
}
