package com.chrono.response.dashboard;

import java.util.List;

public record DashboardResponse(
    Long totalProjects,
    List<ProjectStatusCount> projectStatusCounts,
    Long totalActivities,
    Double totalHours,
    List<ProjectHoursData> projectHoursData,
    List<UserHoursData> userHoursData,
    List<UserPendingActivities> pendingActivitiesByUser
) {}
