package com.chrono.infra.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Classe que representa a resposta de erro.
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private String details;
}
