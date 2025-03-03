package com.chrono.service.dashboard;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.chrono.domain.activity.ActivityStatus;
import com.chrono.response.dashboard.PendingActivityData;
import com.chrono.response.dashboard.UserPendingActivities;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PendingActivitiesService {
    
    private final JdbcTemplate jdbcTemplate;
    
    /**
     * Recupera as atividades pendentes, agrupadas por usuário.
     * 
     * @return Lista de UserPendingActivities, onde cada entrada contém informações do usuário e suas atividades pendentes
     */
    public List<UserPendingActivities> getPendingActivitiesByUser() {
        String sql = """
            SELECT 
                a.id_atividade as activity_id, 
                a.nome as activity_name, 
                a.status as activity_status, 
                a.data_fim as deadline, 
                p.id_projeto as project_id, 
                p.nome as project_name,
                u.id_usuario as user_id, 
                u.nome as user_name
            FROM 
                atividade a
                JOIN projeto p ON a.id_projeto = p.id_projeto
                JOIN usuario u ON a.id_usuario_responsavel = u.id_usuario
            WHERE 
                a.status IN ('ABERTA', 'EM_ANDAMENTO', 'PAUSADA')
            ORDER BY 
                u.nome, a.data_fim
        """;
        
        Map<Integer, List<PendingActivityData>> pendingByUser = new HashMap<>();
        Map<Integer, String> userNames = new HashMap<>();
        
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            
            for (Map<String, Object> row : rows) {
                Integer activityId = (Integer) row.get("activity_id");
                String activityName = (String) row.get("activity_name");
                String statusStr = (String) row.get("activity_status");
                ActivityStatus status = ActivityStatus.valueOf(statusStr);
                
                Date deadlineDate = (Date) row.get("deadline");
                LocalDate deadline = deadlineDate != null ? deadlineDate.toLocalDate() : null;
                
                Integer projectId = (Integer) row.get("project_id");
                String projectName = (String) row.get("project_name");
                Integer userId = (Integer) row.get("user_id");
                String userName = (String) row.get("user_name");
                
                PendingActivityData activityData = new PendingActivityData(
                    activityId, activityName, status, deadline,
                    projectId, projectName, userId, userName
                );
                
                // Guardar o nome do usuário para uso posterior
                userNames.putIfAbsent(userId, userName);
                
                // Adicionar a atividade à lista de atividades do usuário
                pendingByUser.putIfAbsent(userId, new ArrayList<>());
                pendingByUser.get(userId).add(activityData);
            }
        } catch (Exception e) {
            // Em caso de erro, retorna lista vazia
            return new ArrayList<>();
        }
        
        // Converter o mapa em uma lista de UserPendingActivities
        List<UserPendingActivities> result = new ArrayList<>();
        for (Map.Entry<Integer, List<PendingActivityData>> entry : pendingByUser.entrySet()) {
            Integer userId = entry.getKey();
            String userName = userNames.get(userId);
            List<PendingActivityData> activities = entry.getValue();
            
            // Usar o método estático create em vez do construtor personalizado
            result.add(UserPendingActivities.create(userId, userName, activities));
        }
        
        return result;
    }
}
