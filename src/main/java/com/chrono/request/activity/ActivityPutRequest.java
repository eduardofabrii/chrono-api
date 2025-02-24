package com.chrono.request.activity;

import java.time.LocalDate;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.user.UserGetResponseToProject;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActivityPutRequest(
    Integer id,
    
    @NotNull(message = "Projeto é obrigatório.")
    ProjectPostResponse project,
    
    @NotBlank(message = "Nome é obrigatório.")
    String name,
    
    String description,
    
    @NotNull(message = "Data de início é obrigatória.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate,
    
    @NotNull(message = "Data de término é obrigatória.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate,
    
    @NotNull(message = "Status é obrigatório.")
    ActivityStatus status,
    
    UserGetResponseToProject responsible
) {}
