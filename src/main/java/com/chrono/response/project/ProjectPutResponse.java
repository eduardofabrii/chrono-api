package com.chrono.response.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.project.ProjectPriority;
import com.chrono.domain.project.ProjectStatus;
import com.chrono.response.user.UserGetResponseToProject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectPutResponse {
    private Integer id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private UserGetResponseToProject responsible;
    private LocalDateTime creationDate;
    private ProjectPriority priority;
}