package com.chrono.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.repository.ActivityRepository;
import com.chrono.service.project.ProjectService;
import com.chrono.service.user.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ProjectService projectService;
    private final UserService userService;

    // GET to list all activity
    @Override
    public List<Activity> findAllActivities() {
        if (activityRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("Nenhuma atividade encontrada.");
        }
        return activityRepository.findAll();
    }

    // GET to find activity by name
    @Override
    public List<Activity> findActivityByName(String name) {
        return activityRepository.findByNameContaining(name);
    }

    // GET to find activity by id
    @Override
    public Activity findActivityById(Integer id) {
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.orElse(null);
    }

    @Override
    public Activity findActivityById(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.orElse(null);
    }

    // PUT to update activity
    @Override
    public void updateActivity(Activity activity) {
        Activity currentActivity = this.findActivityById(activity.getId());
        currentActivity.setName(activity.getName());
        currentActivity.setDescription(activity.getDescription());
        currentActivity.setProject(activity.getProject());
        currentActivity.setStatus(activity.getStatus());
        currentActivity.setResponsible(activity.getResponsible());
        currentActivity.setStartDate(activity.getStartDate());
        currentActivity.setEndDate(activity.getEndDate());

        activityRepository.save(currentActivity);
    }

    // POST to save Activity
    @Override
    public Activity saveActivity(Activity activity) {

        // Date validator
        if (activity.getEndDate() != null && activity.getStartDate() != null && activity.getEndDate().isBefore(activity.getStartDate())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
    
        activity.setId(null);
        return activityRepository.save(activity);
    }

    // DELETE to delete Activity
    @Override
    public void deleteActivityById(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new EntityNotFoundException("Atividade não encontrada para o ID: " + id);
        }
        activityRepository.deleteById(id);
    }
}