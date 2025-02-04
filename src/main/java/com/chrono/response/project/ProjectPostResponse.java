package com.chrono.response.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.project.ProjectPriority;
import com.chrono.domain.project.ProjectStatus;
import com.chrono.response.user.UserGetResponseToProject;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ProjectPostResponse(
    Integer id,
    String name,
    String description,
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate,
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate,
    
    ProjectStatus status,
    UserGetResponseToProject responsible,
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime creationDate,
    
    ProjectPriority priority
) {}