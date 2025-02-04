package com.chrono.service.project;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chrono.domain.project.Project;
import com.chrono.repository.ProjectRepository;
import com.chrono.service.user.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    // GET to list all projects
    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    // GET to find project by name
    @Override
    public List<Project> findProjectByName(String name) {
        return projectRepository.findByNameContaining(name);
    }

    // GET to find project by id
    @Override
    public Project findProjectById(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }

    // Search user before PUT, for PUT to update project 
    @Override
    public void updateProject(Project project) {
        Project currentProject = this.findProjectById(project.getId());
        currentProject.setName(project.getName());
        currentProject.setDescription(project.getDescription());
        currentProject.setStartDate(project.getStartDate());
        currentProject.setEndDate(project.getEndDate());
        currentProject.setResponsible(project.getResponsible());
        currentProject.setPriority(project.getPriority());
        currentProject.setStatus(project.getStatus());

        if (project.getCreationDate() == null) {
            project.setCreationDate(currentProject.getCreationDate());
        }

        projectRepository.save(currentProject);
    }


    @Override
    public Project saveProject(Project project) {
        // Date validation
        if (project.getEndDate() != null && project.getStartDate() != null && project.getEndDate().isBefore(project.getStartDate())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior a data de início.");
        }
    
        project.setId(null);
        return projectRepository.save(project);
    }

    // DELETE to delete project
    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}