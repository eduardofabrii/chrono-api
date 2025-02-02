package com.chrono.request.activity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.user.UserGetResponseToProject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActivityPostRequest {
    private Integer id;
    private ProjectPostResponse project;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ActivityStatus status;
    private UserGetResponseToProject responsible;
    private LocalDateTime creationDate;
}