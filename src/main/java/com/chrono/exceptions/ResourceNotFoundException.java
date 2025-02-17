package com.chrono.exceptions;

// Classe de exceção personalizada para lidar quando o recurso não for encontrado
public class ResourceNotFoundException extends CustomException {
    
    // Recebe uma mensagem e a passa para a superclasse com um código de status 404
    public ResourceNotFoundException(String mensagem) {
        super(mensagem, 404);
    }
}