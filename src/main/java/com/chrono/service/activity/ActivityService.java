package com.chrono.service.activity;

import java.util.List;

import com.chrono.domain.activity.Activity;

public interface ActivityService {
    public List<Activity> findAllActivities();
    public List<Activity> findActivityByName(String name);
    public Activity findActivityById(Integer id);
    public void updateActivity(Activity Activity);
    public Activity saveActivity(Activity Activity);
    public void deleteActivityById(Long id);
}