package com.chrono.response.dashboard;

import java.util.List;

public record DashboardResponse(
    Long totalProjects,
    List<ProjectStatusCount> projectStatusCounts
) {}
