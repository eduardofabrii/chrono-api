package com.chrono.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.project.ProjectStatus;
import com.chrono.repository.DashboardRepository;
import com.chrono.response.dashboard.DashboardResponse;
import com.chrono.response.dashboard.ProjectHoursData;
import com.chrono.response.dashboard.ProjectStatusCount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;
    private final ProjectHoursService projectHoursService;

    /**
     * Recupera os dados do dashboard
     * @return Objeto DashboardResponse contendo todas as informações
     */
    @Override
    public DashboardResponse getDashboardData() {
        // Obter dados básicos
        Long totalProjects = dashboardRepository.countTotalProjects();
        Long totalActivities = dashboardRepository.countTotalActivities();
        Double totalHours = dashboardRepository.sumTotalHours();
        
        // Processar contagens de status
        List<ProjectStatusCount> projectStatusCounts = new ArrayList<>();
        for (Object[] result : dashboardRepository.getProjectStatusCounts()) {
            ProjectStatus status = (ProjectStatus) result[0];
            Long count = ((Number) result[1]).longValue();
            projectStatusCounts.add(new ProjectStatusCount(status, count));
        }
        
        // Obter projetos com horas calculadas
        List<ProjectHoursData> projectHoursData = projectHoursService.getProjectsWithHours();
        
        // Retornar resposta completa
        return new DashboardResponse(
            totalProjects, 
            projectStatusCounts, 
            totalActivities, 
            totalHours, 
            projectHoursData
        );
    }
}