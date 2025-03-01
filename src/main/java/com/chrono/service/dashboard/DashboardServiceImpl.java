package com.chrono.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.project.ProjectStatus;
import com.chrono.repository.DashboardRepository;
import com.chrono.response.dashboard.DashboardResponse;
import com.chrono.response.dashboard.ProjectStatusCount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    /**
     * Recupera os dados do dashboard, incluindo o número total de projetos e a contagem de projetos por status.
     *
     * @return um objeto {@link DashboardResponse} contendo o número total de projetos e uma lista de contagens de status de projetos.
     */
    @Override
    public DashboardResponse getDashboardData() {
        Long totalProjects = dashboardRepository.countTotalProjects();
        List<Object[]> statusCounts = dashboardRepository.getProjectStatusCounts();
        List<ProjectStatusCount> projectStatusCounts = new ArrayList<>();
        
        for (Object[] result : statusCounts) {
            ProjectStatus status = (ProjectStatus) result[0];
            Long count = ((Number) result[1]).longValue();
            projectStatusCounts.add(new ProjectStatusCount(status, count));
        }
        
        return new DashboardResponse(totalProjects, projectStatusCounts);
    }
}