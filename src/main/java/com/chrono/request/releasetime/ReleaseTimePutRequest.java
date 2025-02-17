package com.chrono.request.releasetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.user.User;
import com.chrono.response.activity.ActivityGetResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReleaseTimePutRequest(
    Integer id,
    
    @NotNull(message = "Atividade é obrigatória")
    ActivityGetResponse activity,
    
    @NotNull(message = "Usuário é obrigatório")
    User user,
    
    @Size(max = 255, message = "Descrição deve ter menos de 255 caracteres")
    String description,
    
    @NotNull(message = "Data de início é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime startDate,
    
    @NotNull(message = "Data de término é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime endDate
) {}
