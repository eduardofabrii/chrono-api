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
}
