package com.chrono.response.dashboard;

import java.time.LocalDate;

import com.chrono.domain.activity.ActivityStatus;

/**
 * Representa os dados de uma atividade pendente para o dashboard.
 */
public record PendingActivityData(
    Integer activityId,
    String activityName,
    ActivityStatus status,
    LocalDate deadline,
    Integer projectId,
    String projectName,
    Integer userId,
    String userName
) {
    /**
     * Verifica se a atividade está atrasada (data de término no passado).
     * 
     * @return true se a atividade estiver atrasada, false caso contrário
     */
    public boolean isOverdue() {
        return deadline != null && deadline.isBefore(LocalDate.now());
    }
    
    /**
     * Calcula quantos dias faltam para o prazo ou, se estiver atrasada, há quantos dias está atrasada.
     * 
     * @return número de dias (positivo = dias restantes, negativo = dias de atraso)
     */
    public long daysToDeadline() {
        if (deadline == null) {
            return 0;
        }
        return LocalDate.now().until(deadline).getDays();
    }
}
