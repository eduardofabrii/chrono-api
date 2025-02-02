package com.chrono.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.repository.ActivityRepository;
import com.chrono.service.project.ProjectService;
import com.chrono.service.user.UserService;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // GET to list all activity
    @Override
    public List<Activity> findAllActivities() {
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

    // PUT to update activity
    @Override
    public void updateActivity(Activity activity) {
        checkAndSetProjectAndResponsible(activity);

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
    
        checkAndSetProjectAndResponsible(activity);
        activity.setId(null);
        return activityRepository.save(activity);
    }

    // DELETE to delete Activity
    @Override
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    // Function for check before post / put in postman
    private void checkAndSetProjectAndResponsible(Activity activity) {
        if (activity.getProject() != null && activity.getProject().getId() != null) {
            activity.setProject(projectService.findProjectById(activity.getProject().getId()));
        }

        if (activity.getResponsible() != null && activity.getResponsible().getId() != null) {
            activity.setResponsible(userService.findResponsibleById(activity.getResponsible().getId().longValue()));
        }

        if (activity.getCreationDate() == null) {
            activity.setCreationDate(activity.getCreationDate());
        }
    }
}