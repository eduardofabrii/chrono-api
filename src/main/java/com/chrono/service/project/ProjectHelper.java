package com.chrono.service.project;

import com.chrono.domain.project.Project;
import com.chrono.domain.user.User;
import com.chrono.repository.UserRepository;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Classe auxiliar para operações relacionadas a projetos.
 */
@Component
@RequiredArgsConstructor
public class ProjectHelper {

    private final UserRepository userRepository;

    /**
     * Define o responsável pelo projeto.
     *
     * @param project       O projeto a ser atualizado.
     * @param responsibleId O ID do responsável a ser definido.
     * @throws ResourceNotFoundException Se o responsável não for encontrado.
     */
    public void setResponsible(Project project, Integer responsibleId) {
        if (responsibleId != null) {
            User responsible = userRepository.findById(responsibleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Responsible not found"));
            project.setResponsible(responsible);
        }
    }

    /**
     * Atualiza os campos do projeto com base nos dados fornecidos.
     *
     * @param project O projeto a ser atualizado.
     * @param dto     O objeto contendo os novos dados do projeto.
     */
    public void updateProjectFields(Project project, ProjectPutRequest dto) {
        if (dto.name() != null) project.setName(dto.name());
        if (dto.description() != null) project.setDescription(dto.description());
        if (dto.startDate() != null) project.setStartDate(dto.startDate());
        if (dto.endDate() != null) project.setEndDate(dto.endDate());
        if (dto.priority() != null) project.setPriority(dto.priority());
        if (dto.status() != null) project.setStatus(dto.status());

        // Atualizar o responsável
        setResponsible(project, dto.responsible().getId());
    }
}
