package com.chrono.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrono.domain.project.Project;
import com.chrono.domain.project.ProjectPriority;
import com.chrono.domain.project.ProjectStatus;
import com.chrono.domain.user.User;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.LocalDate;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findByName(String name);
    public List<Project> findByNameContaining(String name);
    public Optional<Project> findById(Integer id);
    public List<Project> findByPriority(ProjectPriority priority);
    public List<Project> findByCreationDate(LocalDateTime creationDate);
    public List<Project> findByResponsible(User responsible);
    public List<Project> findByStartDate(LocalDate startDate);
    public List<Project> findByStatus(ProjectStatus status);
    public List<Project> findByEndDate(LocalDate endDate);
}