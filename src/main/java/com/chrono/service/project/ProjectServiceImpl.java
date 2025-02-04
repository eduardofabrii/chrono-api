package com.chrono.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.project.Project;
import com.chrono.domain.user.User;
import com.chrono.mapper.ProjectMapper;
import com.chrono.repository.ProjectRepository;
import com.chrono.repository.UserRepository;
import com.chrono.request.project.ProjectPostRequest;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.project.ProjectPutResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper mapper;

    // GET all projects
    @Override
    public List<ProjectGetResponse> findAllProjects() {
        return mapper.toProjectGetResponseList(projectRepository.findAll());
    }

    // GET project by name
    @Override
    public List<ProjectGetResponse> findProjectByName(String name) {
        return mapper.toProjectGetResponseList(projectRepository.findByNameContaining(name));
    }

    // GET project by id
    @Override
    public ProjectGetResponse findProjectById(Integer id) {
        return mapper.toProjectGetResponse(
                projectRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Project not found"))
        );
    }

    // PUT to update project
    @Override
    public ProjectPutResponse updateProject(Integer id, ProjectPutRequest dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (dto.name() != null) project.setName(dto.name());
        if (dto.description() != null) project.setDescription(dto.description());
        if (dto.startDate() != null) project.setStartDate(dto.startDate());
        if (dto.endDate() != null) project.setEndDate(dto.endDate());
        if (dto.responsible() != null) project.setResponsible(dto.responsible());
        if (dto.priority() != null) project.setPriority(dto.priority());
        if (dto.status() != null) project.setStatus(dto.status());

        setResponsible(project, dto.responsible().getId());

        projectRepository.save(project);
        return mapper.toProjectPutResponse(project);
    }

    // POST new project
    @Override
    public ProjectPostResponse saveProject(ProjectPostRequest postRequest) {
        Project project = mapper.toProjectPost(postRequest);
        setResponsible(project, postRequest.responsible().id());
        projectRepository.save(project);
        return mapper.toProjectPostResponse(project);
    }

    // DELETE project by ID
    @Override
    public void deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }

    
    // Function to fetch and set responsible user
    private void setResponsible(Project project, Integer responsibleId) {
        if (responsibleId != null) {
            User responsible = userRepository.findById(responsibleId)
                    .orElseThrow(() -> new RuntimeException("Responsible not found"));
            project.setResponsible(responsible);
        }
    }
}
