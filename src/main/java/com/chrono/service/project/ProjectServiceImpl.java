package com.chrono.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.project.Project;
import com.chrono.mapper.ProjectMapper;
import com.chrono.repository.ProjectRepository;
import com.chrono.request.project.ProjectPostRequest;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.project.ProjectPutResponse;
import com.chrono.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

// Implementação do serviço de projetos.
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;
    private final ProjectHelper projectHelper;

    /**
     * Obtém todos os projetos.
     * 
     * @return uma lista de respostas de projetos.
     */
    @Override
    public List<ProjectGetResponse> findAllProjects() {
        return mapper.toProjectGetResponseList(projectRepository.findAll());
    }

    /**
     * Obtém projetos pelo nome.
     * 
     * @param name o nome do projeto a ser buscado.
     * @return uma lista de respostas de projetos que contêm o nome fornecido.
     * @throws ResourceNotFoundException se nenhum projeto for encontrado.
     */
    @Override
    public List<ProjectGetResponse> findProjectByName(String name) {
        List<Project> projects = projectRepository.findByNameContaining(name);

        if (projects.isEmpty()) {
            throw new ResourceNotFoundException("Project not found");
        }

        return mapper.toProjectGetResponseList(projects);
    }

    /**
     * Obtém um projeto pelo ID.
     * 
     * @param id o ID do projeto a ser buscado.
     * @return a resposta do projeto encontrado.
     * @throws ResourceNotFoundException se o projeto não for encontrado.
     */
    @Override
    public ProjectGetResponse findProjectById(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return mapper.toProjectGetResponse(project);
    }

    /**
     * Atualiza um projeto existente.
     * 
     * @param id o ID do projeto a ser atualizado.
     * @param dto os dados de atualização do projeto.
     * @return a resposta do projeto atualizado.
     * @throws ResourceNotFoundException se o projeto não for encontrado.
     */
    @Override
    public ProjectPutResponse updateProject(Integer id, ProjectPutRequest dto) {
        projectHelper.validateResponsibleRole(dto.responsible().getId());
        
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        // Delegar a atualização dos campos do projeto para o helper
        projectHelper.updateProjectFields(project, dto);

        // Salvar o projeto atualizado
        projectRepository.save(project);
        return mapper.toProjectPutResponse(project);
    }

    /**
     * Salva um novo projeto.
     * 
     * @param postRequest os dados do novo projeto.
     * @return a resposta do projeto salvo.
     */
    @Override
    public ProjectPostResponse saveProject(ProjectPostRequest postRequest) {
        projectHelper.validateResponsibleRole(postRequest.responsible().id());
        Project project = mapper.toProjectPost(postRequest);
        projectHelper.setResponsible(project, postRequest.responsible().id());
        projectRepository.save(project);
        return mapper.toProjectPostResponse(project);
    }

    /**
     * Exclui um projeto pelo ID.
     * 
     * @param id o ID do projeto a ser excluído.
     * @throws ResourceNotFoundException se o projeto não for encontrado.
     */
    @Override
    public void deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found");
        }

        projectRepository.deleteById(id);
    }
}
