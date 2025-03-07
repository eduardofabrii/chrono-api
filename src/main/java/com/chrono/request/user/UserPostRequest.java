package com.chrono.request.user;

import com.chrono.domain.user.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPostRequest(
    Integer id,

    @NotBlank(message = "O nome do usuário é obrigatório.")
    @Size(min = 3, max = 70, message = "O nome deve ter entre 3 e 70 caracteres")
    String name,

    @NotBlank(message = "O email do usuário é obrigatório.")
    @Email(message = "O email está inválido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    String email,

    @NotBlank(message = "É obrigatório o usuário ter uma senha.")
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres")
    String password,

    UserRole role
) {}
