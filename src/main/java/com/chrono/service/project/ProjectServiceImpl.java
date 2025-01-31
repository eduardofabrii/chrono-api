package com.chrono.service.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrono.domain.project.Project;
import com.chrono.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

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

    // PUT to update project
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

        projectRepository.save(currentProject);
    }

    // POST to save project
    @Override
    public Project saveProject(Project project) {
        project.setId(null);
        return projectRepository.save(project);
    }

    // DELETE to delete project
    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}