package com.chrono.response.dashboard;

/**
 * Representa os dados de horas lançadas por um usuário para o dashboard.
 */
public record UserHoursData(
    Integer userId,
    String userName,
    String initials,
    Double totalHours
) {
    /**
     * Construtor que automaticamente gera as iniciais a partir do nome completo.
     * 
     * @param userId ID do usuário
     * @param userName Nome completo do usuário
     * @param totalHours Total de horas lançadas pelo usuário
     */
    public UserHoursData(Integer userId, String userName, Double totalHours) {
        this(userId, userName, generateInitials(userName), totalHours);
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
