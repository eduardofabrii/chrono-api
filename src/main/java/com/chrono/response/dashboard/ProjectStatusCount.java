package com.chrono.response.dashboard;

import com.chrono.domain.project.ProjectStatus;

public record ProjectStatusCount(
    ProjectStatus status,
    Long count
) {}
