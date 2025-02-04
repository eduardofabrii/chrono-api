package com.chrono.response.activity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.user.UserGetResponseToProject;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ActivityPutResponse(
    Integer id,
    ProjectGetResponse project,
    String name,
    String description,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate,

    ActivityStatus status,
    UserGetResponseToProject responsible,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime creationDate
) {}