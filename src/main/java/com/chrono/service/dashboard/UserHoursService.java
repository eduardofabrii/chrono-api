package com.chrono.service.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.chrono.response.dashboard.UserHoursData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHoursService {
    
    private final JdbcTemplate jdbcTemplate;
    
    /**
     * Recupera a lista de usuários com suas horas totais lançadas.
     * 
     * @return Lista de UserHoursData com os dados de horas lançadas por usuário
     */
    public List<UserHoursData> getUsersWithHours() {
        String sql = """
            SELECT 
                u.id_usuario as id, 
                u.nome as name,
                COALESCE(SUM(TIMESTAMPDIFF(MINUTE, lh.data_inicio, lh.data_fim) / 60.0), 0) as total_hours
            FROM 
                usuario u
                LEFT JOIN lancamento_hora lh ON lh.id_usuario = u.id_usuario
            GROUP BY 
                u.id_usuario, u.nome
            ORDER BY 
                total_hours DESC
        """;
        
        List<UserHoursData> result = new ArrayList<>();
        
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            
            for (Map<String, Object> row : rows) {
                Integer id = (Integer) row.get("id");
                String name = (String) row.get("name");
                Double hours = ((Number) row.get("total_hours")).doubleValue();
                
                // O construtor já gera automaticamente as iniciais
                result.add(new UserHoursData(id, name, hours));
            }
        } catch (Exception e) {
            // Em caso de erro, tenta o método de backup
            result = getUsersFromRepository();
        }
        
        return result;
    }
    
    /**
     * Método de backup para obter usuários e suas horas
     */
    private List<UserHoursData> getUsersFromRepository() {
        String sql = "SELECT id_usuario, nome FROM usuario";
        List<UserHoursData> result = new ArrayList<>();
        
        try {
            List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
            
            for (Map<String, Object> user : users) {
                Integer userId = (Integer) user.get("id_usuario");
                String name = (String) user.get("nome");
                
                String hoursSql = """
                    SELECT COALESCE(SUM(TIMESTAMPDIFF(MINUTE, data_inicio, data_fim) / 60.0), 0) 
                    FROM lancamento_hora 
                    WHERE id_usuario = ?
                """;
                
                Double hours = jdbcTemplate.queryForObject(hoursSql, Double.class, userId);
                if (hours == null) hours = 0.0;
                
                result.add(new UserHoursData(userId, name, hours));
            }
        } catch (Exception e) {
            // Se falhar completamente, retorna lista vazia
        }
        
        return result;
    }
}
