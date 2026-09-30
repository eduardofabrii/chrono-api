package com.chrono.service.security;

import java.util.Objects;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chrono.domain.user.User;
import com.chrono.domain.user.UserRole;

/**
 * Regras de acesso a recursos que pertencem a um usuário (perfil e lançamentos de horas).
 */
@Component
public class AccessGuard {

    /**
     * Retorna o usuário autenticado na requisição atual.
     */
    public User currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User user) {
            return user;
        }
        throw new AccessDeniedException("Usuário não autenticado");
    }

    public boolean isAdmin() {
        return currentUser().getRole() == UserRole.ADMIN;
    }

    /**
     * Garante que o usuário autenticado é o dono do recurso ou um administrador.
     *
     * @param ownerId o ID do usuário dono do recurso
     * @throws AccessDeniedException se o usuário não tiver permissão
     */
    public void requireSelfOrAdmin(Integer ownerId) {
        if (!isAdmin() && !Objects.equals(currentUser().getId(), ownerId)) {
            throw new AccessDeniedException("Sem permissão para acessar este recurso");
        }
    }
}
