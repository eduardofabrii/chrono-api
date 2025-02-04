package com.chrono.response.releasetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.user.UserGetResponseToProject;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ReleaseTimeGetResponse(
    Integer id,
    ActivityGetResponse activity,
    UserGetResponseToProject user,
    String description,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime registerDate
) {}
