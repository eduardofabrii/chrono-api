package com.chrono.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

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
        activity.setId(null);
        return activityRepository.save(activity);
    }

    // DELETE to delete Activity
    @Override
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }
}