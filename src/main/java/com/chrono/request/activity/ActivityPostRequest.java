package com.chrono.request.activity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.domain.project.Project;
import com.chrono.domain.user.User;
import com.chrono.response.project.ProjectPostResponse;

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
    private User responsible;
    private LocalDateTime creationDate;
}