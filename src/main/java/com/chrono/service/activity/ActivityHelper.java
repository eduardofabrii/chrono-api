package com.chrono.service.activity;

import org.springframework.stereotype.Component;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.project.Project;
import com.chrono.domain.user.User;
import com.chrono.exceptions.ResourceNotFoundException;
import com.chrono.repository.ActivityRepository;
import com.chrono.repository.ProjectRepository;
import com.chrono.repository.UserRepository;
import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;

/**
 * Classe auxiliar para operações relacionadas a atividades.
 */
@Component
public class ActivityHelper {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    /**
     * Construtor para inicializar os repositórios de projeto e usuário.
     *
     * @param projectRepository Repositório de projetos.
     * @param userRepository Repositório de usuários.
     */
    public ActivityHelper(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    /**
     * Obtém uma atividade pelo seu ID.
     *
     * @param id ID da atividade.
     * @param activityRepository Repositório de atividades.
     * @return A atividade encontrada.
     * @throws ResourceNotFoundException Se a atividade não for encontrada.
     */
    public Activity getActivityById(Integer id, ActivityRepository activityRepository) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found"));
    }

    /**
     * Obtém um projeto pelo seu ID.
     *
     * @param projectId ID do projeto.
     * @return O projeto encontrado.
     * @throws ResourceNotFoundException Se o projeto não for encontrado.
     */
    public Project getProjectById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    /**
     * Obtém um usuário pelo seu ID.
     *
     * @param userId ID do usuário.
     * @return O usuário encontrado.
     * @throws ResourceNotFoundException Se o usuário não for encontrado.
     */
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Responsible not found"));
    }

    /**
     * Atualiza os campos de uma atividade com base em um DTO de atualização.
     *
     * @param activity A atividade a ser atualizada.
     * @param dto O DTO contendo os novos valores dos campos.
     */
    public void updateActivityFields(Activity activity, ActivityPutRequest dto) {
        if (dto.name() != null) activity.setName(dto.name());
        if (dto.description() != null) activity.setDescription(dto.description());
        if (dto.startDate() != null) activity.setStartDate(dto.startDate());
        if (dto.endDate() != null) activity.setEndDate(dto.endDate());
        if (dto.status() != null) activity.setStatus(dto.status());
        if (dto.responsible() != null) activity.setResponsible(getUserById(dto.responsible().id()));
    }

    /**
     * Atualiza os campos de uma atividade com base em uma requisição de criação.
     *
     * @param activity A atividade a ser atualizada.
     * @param request A requisição contendo os novos valores dos campos.
     */
    public void updateActivityFields(Activity activity, ActivityPostRequest request) {
        activity.setName(request.name());
        activity.setDescription(request.description());
        activity.setStartDate(request.startDate());
        activity.setEndDate(request.endDate());
        activity.setStatus(request.status());
    }

    /**
     * Valida se as datas de uma atividade estão dentro do intervalo de datas do projeto.
     *
     * @param activity A atividade a ser validada.
     * @throws IllegalArgumentException Se as datas da atividade estiverem fora do intervalo do projeto.
     */
    public void validateActivityDates(Activity activity) {
        Project project = activity.getProject();

        // Verifica se a data de início da atividade é anterior à data de início do projeto
        if (activity.getStartDate().isBefore(project.getStartDate())) {
            throw new IllegalArgumentException("A data de início da atividade não pode ser anterior à data de início do projeto.");
        }

        // Verifica se a data de fim da atividade é posterior à data de fim do projeto
        if (activity.getEndDate().isAfter(project.getEndDate())) {
            throw new IllegalArgumentException("A data de fim da atividade não pode ser posterior à data de fim do projeto.");
        }

        // Verifica se a data de início da atividade é posterior à data de fim
        if (activity.getStartDate().isAfter(activity.getEndDate())) {
            throw new IllegalArgumentException("A data de início da atividade não pode ser posterior à data de fim da atividade.");
        }
    }
}
