package com.chrono.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.mapper.ActivityMapper;
import com.chrono.repository.ActivityRepository;
import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.activity.ActivityPostResponse;
import com.chrono.response.activity.ActivityPutResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper mapper;

    // GET to list all activities
    @Override
    public List<ActivityGetResponse> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return mapper.toActivityGetResponseList(activities);
    }

    // GET to find activity by name
    @Override
    public List<ActivityGetResponse> findActivityByName(String name) {
        List<Activity> activities = activityRepository.findByNameContaining(name);
        return mapper.toActivityGetResponseList(activities);
    }

    // GET to find activity by id
    @Override
    public ActivityGetResponse findActivityById(Integer id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        return mapper.toActivityGetResponse(activity);
    }

    // PUT to update activity
    @Override
    public ActivityPutResponse updateActivity(Integer id, ActivityPutRequest dto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        if (dto.name() != null) activity.setName(dto.name());
        if (dto.description() != null) activity.setDescription(dto.description());
        if (dto.status() != null) activity.setStatus(dto.status());

        activityRepository.save(activity);
        return mapper.toActivityPutResponse(activity);
    }

    // POST to save activity
    @Override
    public ActivityPostResponse saveActivity(ActivityPostRequest postRequest) {
        Activity activity = mapper.toActivityPost(postRequest);
        activityRepository.save(activity);
        return mapper.toActivityPostResponse(activity);
    }

    // DELETE to delete activity
    @Override
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }
}