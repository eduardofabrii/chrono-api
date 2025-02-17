package com.chrono.service.user;

import com.chrono.domain.user.User;
import com.chrono.request.user.UserPutRequest;

import org.springframework.stereotype.Component;

/**
 * Classe auxiliar para manipulação dos dados relacionados aos usuários.
 * 
 * Esta classe fornece métodos para atualizar os campos do usuário e validar as informações.
 */
@Component
public class UserHelper {

    /**
     * Atualiza os campos do objeto User com os dados fornecidos no DTO.
     * 
     * @param user o objeto User a ser atualizado.
     * @param dto os dados de atualização do User.
     */
    public void updateUserFields(User user, UserPutRequest dto) {
        if (dto.name() != null) user.setName(dto.name());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.password() != null) user.setPassword(dto.password());
        if (dto.role() != null) user.setRole(dto.role());
    }
}
