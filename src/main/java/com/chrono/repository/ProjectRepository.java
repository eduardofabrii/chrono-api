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
    List<Project> findByName(String name);
    List<Project> findByNameContaining(String name);
    Optional<Project> findById(Integer id);
    List<Project> findByPriority(ProjectPriority priority);
    List<Project> findByCreationDate(LocalDateTime creationDate);
    List<Project> findByResponsible(User responsible);
    List<Project> findByStartDate(LocalDate startDate);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByEndDate(LocalDate endDate);
    
}