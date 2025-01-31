package com.chrono.service.project;

import java.util.List;

import com.chrono.domain.project.Project;

public interface ProjectService {
    public List<Project> findAllProjects();
    public List<Project> findProjectByName(String name);
    public Project findProjectById(Integer id);
    public void updateProject(Project project);
    public Project saveProject(Project project);
    public void deleteProjectById(Long id);
}