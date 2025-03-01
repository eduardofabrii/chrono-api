package com.chrono.service.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.chrono.domain.project.ProjectStatus;
import com.chrono.response.dashboard.ProjectHoursData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectHoursService {
    
    private final JdbcTemplate jdbcTemplate;
    
    /**
     * Recupera a lista de projetos com suas horas totais calculadas.
     * 
     * @return Lista de ProjectHoursData com os dados de horas calculados
     */
    public List<ProjectHoursData> getProjectsWithHours() {
        String sql = """
            SELECT 
                p.id_projeto as id, 
                p.nome as name, 
                p.status as status,
                COALESCE(SUM(TIMESTAMPDIFF(MINUTE, lh.data_inicio, lh.data_fim) / 60.0), 0) as total_hours
            FROM 
                projeto p
                LEFT JOIN atividade a ON a.id_projeto = p.id_projeto
                LEFT JOIN lancamento_hora lh ON lh.id_atividade = a.id_atividade
            GROUP BY 
                p.id_projeto, p.nome, p.status
            ORDER BY 
                total_hours DESC
        """;
        
        List<ProjectHoursData> result = new ArrayList<>();
        
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            
            for (Map<String, Object> row : rows) {
                Integer id = (Integer) row.get("id");
                String name = (String) row.get("name");
                String statusStr = (String) row.get("status");
                Double hours = ((Number) row.get("total_hours")).doubleValue();
                
                ProjectStatus status = ProjectStatus.valueOf(statusStr);
                result.add(new ProjectHoursData(id, name, status, hours));
            }
        } catch (Exception e) {
            // Em caso de erro, tenta o método de backup
            result = getProjectsFromRepository();
        }
        
        return result;
    }
    
    /**
     * Método de backup para obter projetos e suas horas
     */
    private List<ProjectHoursData> getProjectsFromRepository() {
        String sql = "SELECT id_projeto, nome, status FROM projeto";
        List<ProjectHoursData> result = new ArrayList<>();
        
        try {
            List<Map<String, Object>> projects = jdbcTemplate.queryForList(sql);
            
            for (Map<String, Object> project : projects) {
                Integer projectId = (Integer) project.get("id_projeto");
                String name = (String) project.get("nome");
                String statusStr = (String) project.get("status");
                ProjectStatus status = ProjectStatus.valueOf(statusStr);
                
                String hoursSql = """
                    SELECT COALESCE(SUM(TIMESTAMPDIFF(MINUTE, lh.data_inicio, lh.data_fim) / 60.0), 0) 
                    FROM atividade a 
                    JOIN lancamento_hora lh ON lh.id_atividade = a.id_atividade 
                    WHERE a.id_projeto = ?
                """;
                
                Double hours = jdbcTemplate.queryForObject(hoursSql, Double.class, projectId);
                if (hours == null) hours = 0.0;
                
                result.add(new ProjectHoursData(projectId, name, status, hours));
            }
        } catch (Exception e) {
            // Se falhar completamente, retorna lista vazia
        }
        
        return result;
    }
}
