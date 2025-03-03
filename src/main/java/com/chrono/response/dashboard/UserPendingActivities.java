package com.chrono.response.dashboard;

import java.util.List;

/**
 * Representa o agrupamento de atividades pendentes por usuário.
 */
public record UserPendingActivities(
    Integer userId,
    String userName,
    String initials,
    List<PendingActivityData> pendingActivities
) {
    /**
     * Construtor alternativo que gera as iniciais automaticamente.
     * Este construtor deve ser público para que possa ser usado por outras classes.
     * 
     * @param userId ID do usuário
     * @param userName Nome completo do usuário
     * @param pendingActivities Lista de atividades pendentes
     */
    public static UserPendingActivities create(Integer userId, String userName, List<PendingActivityData> pendingActivities) {
        return new UserPendingActivities(userId, userName, generateInitials(userName), pendingActivities);
    }
    
    /**
     * Gera as iniciais a partir do nome completo (até 2 primeiros nomes).
     * 
     * @param fullName Nome completo
     * @return Iniciais do usuário (ex: "Eduardo Henrique Fabri" retorna "EH")
     */
    private static String generateInitials(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return "";
        }
        
        String[] nameParts = fullName.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();
        
        // Pega a primeira inicial
        if (nameParts.length > 0 && !nameParts[0].isEmpty()) {
            initials.append(Character.toUpperCase(nameParts[0].charAt(0)));
        }
        
        // Pega a segunda inicial, se existir
        if (nameParts.length > 1 && !nameParts[1].isEmpty()) {
            initials.append(Character.toUpperCase(nameParts[1].charAt(0)));
        }
        
        return initials.toString();
    }
}
