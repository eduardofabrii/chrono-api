package com.chrono.service.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chrono.domain.user.User;

// Serviço responsável pela geração e validação de tokens JWT.
@Service
public class TokenService {

    // Secret key
    @Value("${api.security.token.secret}")
    private String secret;
    
    /**
     * Gera um token JWT para o usuário fornecido.
     *
     * @param user O usuário para o qual o token será gerado.
     * @param role O papel do usuário.
     * @return O token JWT gerado.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token.
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Token
            return JWT.create()
            .withIssuer("chrono_api")
            .withSubject(user.getName())
            .withClaim("role", user.getRole().name())
            .withClaim("id", user.getId().toString())
            .withExpiresAt(this.generateExpirationDate())
            .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating", exception);
        }
    }

    /**
     * Valida o token JWT fornecido.
     *
     * @param token O token JWT a ser validado.
     * @return O assunto (subject) contido no token se for válido, ou null se for inválido.
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("chrono_api")
            .build()
            .verify(token)
            .getSubject(); // Get the value saved inside token (email)
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * Gera a data de expiração do token JWT.
     *
     * @return A data de expiração do token.
     */
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}