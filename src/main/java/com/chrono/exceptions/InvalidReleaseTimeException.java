package com.chrono.exceptions;

// Exceção lançada quando um horário de lançamento inválido é encontrado.
public class InvalidReleaseTimeException extends RuntimeException {
    public InvalidReleaseTimeException(String message) {
        super(message);
    }
}
