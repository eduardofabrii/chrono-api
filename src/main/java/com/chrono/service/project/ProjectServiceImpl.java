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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;

    // GET all projects
    @Override
    public List<ProjectGetResponse> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return mapper.toProjectGetResponseList(projects);
    }

    // GET project by name
    @Override
    public List<ProjectGetResponse> findProjectByName(String name) {
        List<Project> projects = projectRepository.findByNameContaining(name);
        return mapper.toProjectGetResponseList(projects);
    }

    // GET project by id
    @Override
    public ProjectGetResponse findProjectById(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return mapper.toProjectGetResponse(project);
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
        if (dto.responsible() != null) project.setResponsibleToOnlyProject(dto.responsible());
        if (dto.priority() != null) project.setPriority(dto.priority());
        if (dto.status() != null) project.setStatus(dto.status());

        projectRepository.save(project);
        return mapper.toProjectPutResponse(project);
    }

    // POST new project
    @Override
    public ProjectPostResponse saveProject(ProjectPostRequest postRequest) {
        Project project = mapper.toProjectPost(postRequest);
        projectRepository.save(project);
        return mapper.toProjectPostResponse(project);
    }

    // DELETE project by ID
    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
