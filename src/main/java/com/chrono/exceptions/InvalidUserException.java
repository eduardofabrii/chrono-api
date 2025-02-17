package com.chrono.exceptions;

// Esta exceção é lançada quando há problemas com os dados fornecidos para um usuário
public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
