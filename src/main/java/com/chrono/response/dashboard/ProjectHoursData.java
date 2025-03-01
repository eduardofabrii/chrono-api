package com.chrono.response.dashboard;

import com.chrono.domain.project.ProjectStatus;

public record ProjectHoursData(
    Integer projectId,
    String projectName,
    ProjectStatus projectStatus,
    Double totalHours
) {
}
