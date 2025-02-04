package com.chrono.request.releasetime;

import java.time.LocalDate;

import com.chrono.domain.user.User;
import com.chrono.response.activity.ActivityGetResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.FutureOrPresent;

public record ReleaseTimePostRequest(
    Integer id,
    
    @NotNull(message = "Atividade é obrigatória")
    ActivityGetResponse activity,
    
    @NotNull(message = "Usuário é obrigatório")
    User user,
    
    @Size(max = 255, message = "Descrição deve ter menos de 255 caracteres")
    String description,
    
    @NotNull(message = "Data de início é obrigatória")
    @PastOrPresent(message = "Data de início deve estar no passado ou presente")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate,
    
    @NotNull(message = "Data de término é obrigatória")
    @FutureOrPresent(message = "Data de término deve estar no presente ou futuro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate
) {}
