package com.chrono.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.activity.ActivityStatus;

import java.util.List;
import java.util.Optional;

import com.chrono.domain.project.Project;
import com.chrono.domain.user.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public List<Activity> findByName(String name);
    public List<Activity> findByNameContaining(String name);
    public Optional<Activity> findById(Integer id);
    public List<Activity> findByProject(Project project);
    public List<Activity> findByProjectId(Integer projectId);
    public List<Activity> findByResponsible(User responsible);
    public List<Activity> findByEndDate(LocalDate endDate);
    public List<Activity> findByCreationDate(LocalDateTime creationDate);
    public List<Activity> findByStatus(ActivityStatus status);
    public List<Activity> findByStartDate(LocalDate startDate);
}