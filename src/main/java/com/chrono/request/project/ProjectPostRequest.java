package com.chrono.request.project;

import java.time.LocalDate;

import com.chrono.domain.project.ProjectPriority;
import com.chrono.domain.project.ProjectStatus;
import com.chrono.response.user.UserGetResponseToProject;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectPostRequest(
    Integer id,

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter menos de 100 caracteres")
    String name,

    @Size(max = 500, message = "Descrição deve ter menos de 500 caracteres")
    String description,

    @NotNull(message = "Data de início é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate startDate,

    @NotNull(message = "Data de término é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate endDate,

    @NotNull(message = "Status é obrigatório")
    ProjectStatus status,

    @NotNull(message = "Usuário responsável é obrigatório")
    UserGetResponseToProject responsible,

    @NotNull(message = "Prioridade é obrigatória")
    ProjectPriority priority
) {}
