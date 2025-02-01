package com.chrono.response.activity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.user.UserGetResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActivityGetResponse {
    private Integer id;
    private ProjectGetResponse project;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ActivityStatus status;
    private UserGetResponse responsible;
    private LocalDateTime creationDate;
}
