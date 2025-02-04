package com.chrono.request.activity;

import java.time.LocalDate;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.user.UserGetResponseToProject;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActivityPostRequest(
    Integer id,
    
    @NotNull(message = "Projeto é obrigatório.")
    ProjectPostResponse project,
    
    @NotBlank(message = "Nome é obrigatório.")
    String name,
    
    String description,
    
    @NotNull(message = "Data de início é obrigatória.")
    @FutureOrPresent(message = "Data de início deve estar no presente ou futuro.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate,
    
    @NotNull(message = "Data de término é obrigatória.")
    @FutureOrPresent(message = "Data de término deve estar no presente ou futuro.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate,
    
    @NotNull(message = "Status é obrigatório.")
    ActivityStatus status,
    
    UserGetResponseToProject responsible
) {}
