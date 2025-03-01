package com.chrono.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chrono.domain.project.Project;

public interface DashboardRepository extends JpaRepository<Project, Integer> {
    
    /**
     * Conta o número total de projetos no sistema
     * @return o número total de projetos
     */
    @Query("SELECT COUNT(p) FROM Project p")
    Long countTotalProjects();
    
    /**
     * Obtém a contagem de cada status de projeto (PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO)
     * @return uma lista de arrays contendo status e contagem
     */
    @Query("SELECT p.status as status, COUNT(p) as count FROM Project p GROUP BY p.status")
    List<Object[]> getProjectStatusCounts();
    
    /**
     * Conta o número total de atividades no sistema
     * @return o número total de atividades
     */
    @Query("SELECT COUNT(a) FROM Activity a")
    Long countTotalActivities();
    
    /**
     * Calcula a soma total de horas registradas em todos os lançamentos de tempo.
     * Usa a função TIMESTAMPDIFF do MySQL para calcular a diferença em minutos,
     * que então é convertida para horas com precisão decimal.
     * @return o total de horas lançadas com precisão decimal
     */
    @Query(value = "SELECT COALESCE(SUM(TIMESTAMPDIFF(MINUTE, data_inicio, data_fim) / 60.0), 0) FROM lancamento_hora", nativeQuery = true)
    Double sumTotalHours();
    
    /**
     * Consulta temporária para obter dados básicos dos projetos para uso no dashboard
     * até que possamos resolver a consulta de horas por projeto.
     * @return Lista de projetos com seus ids, nomes e status
     */
    @Query("SELECT p.id, p.name, p.status FROM Project p")
    List<Object[]> getBasicProjectData();
}


