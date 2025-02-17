package com.chrono.infra.security.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chrono.exceptions.InvalidReleaseTimeException;
import com.chrono.exceptions.ResourceNotFoundException;

/**
 * Classe que trata exceções globais na aplicação.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Manipula exceções do tipo ResourceNotFoundException.
     *
     * @param ex a exceção ResourceNotFoundException lançada
     * @return ResponseEntity com detalhes do erro e status HTTP 404 (Not Found)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Manipula exceções do tipo {@link InvalidReleaseTimeException}.
     *
     * @param ex a exceção lançada
     * @return um ResponseEntity contendo a mensagem da exceção e o status BAD_REQUEST
     */
    @ExceptionHandler(InvalidReleaseTimeException.class)
    public ResponseEntity<String> handleInvalidReleaseTime(InvalidReleaseTimeException ex) {
        logger.error("Invalid release time: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções genéricas.
     *
     * @param ex a exceção lançada
     * @return ResponseEntity com detalhes do erro e status HTTP 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        logger.error("Unexpected error: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}