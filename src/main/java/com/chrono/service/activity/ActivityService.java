package com.chrono.service.activity;

import java.util.List;

import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.activity.ActivityPostResponse;
import com.chrono.response.activity.ActivityPutResponse;

public interface ActivityService {
    List<ActivityGetResponse> findAllActivities();
    List<ActivityGetResponse> findActivityByName(String name);
    ActivityGetResponse findActivityById(Integer id);
    ActivityPutResponse updateActivity(Integer id, ActivityPutRequest activity);
    ActivityPostResponse saveActivity(ActivityPostRequest activity);
    void deleteActivityById(Long id);
    List<ActivityGetResponse> findActivitiesByProjectId(Integer projectId);
    List<ActivityGetResponse> findActivitiesByResponsibleUserId(Integer projectId);
}