package com.italo.copiavideo.exceptions;

public class FailedCreateTokenException extends RuntimeException {
    public FailedCreateTokenException(String message) {
        super(message);
    }
    public FailedCreateTokenException() {
        super("Falha ao gerar token");
    }
}
