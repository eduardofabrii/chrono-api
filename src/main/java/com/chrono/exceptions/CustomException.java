package com.chrono.exceptions;

/**
 * CustomException é uma exceção de tempo de execução personalizada que inclui um código de status HTTP.
 */
public class CustomException extends RuntimeException {
    private final int statusCode;

    /**
     * Constrói uma nova CustomException com a mensagem detalhada e o código de status especificados.
     *
     * @param message a mensagem detalhada
     * @param statusCode o código de status HTTP
     */
    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Retorna o código de status HTTP associado a esta exceção.
     *
     * @return o código de status HTTP
     */
    public int getStatusCode() {
        return statusCode;
    }
}