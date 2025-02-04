package com.chrono.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.project.Project;
import com.chrono.domain.user.User;
import com.chrono.mapper.ActivityMapper;
import com.chrono.repository.ActivityRepository;
import com.chrono.repository.ProjectRepository;
import com.chrono.repository.UserRepository;
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
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ActivityMapper mapper;

    // GET all activities
    @Override
    public List<ActivityGetResponse> findAllActivities() {
        return mapper.toActivityGetResponseList(activityRepository.findAll());
    }

    // GET activity by name
    @Override
    public List<ActivityGetResponse> findActivityByName(String name) {
        return mapper.toActivityGetResponseList(activityRepository.findByNameContaining(name));
    }

    // GET activity by id
    @Override
    public ActivityGetResponse findActivityById(Integer id) {
        return mapper.toActivityGetResponse(getActivityById(id));
    }

    // PUT activity by name
    @Override
    public ActivityPutResponse updateActivity(Integer id, ActivityPutRequest dto) {
        Activity activity = getActivityById(id);
        updateActivityFields(activity, dto);
        activityRepository.save(activity);
        return mapper.toActivityPutResponse(activity);
    }

    // POST activity
    @Override
    public ActivityPostResponse saveActivity(ActivityPostRequest request) {
        Activity activity = new Activity();
        updateActivityFields(activity, request);
        activity.setProject(getProjectById(request.project().id()));
        activity.setResponsible(getUserById(request.responsible().id()));
        activityRepository.save(activity);
        return mapper.toActivityPostResponse(activity);
    }

    // DELETE activity by id
    @Override
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    
    // Functions to fetch and set all fields on body request
    private Activity getActivityById(Integer id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    private Project getProjectById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    private User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Responsible not found"));
    }

    private void updateActivityFields(Activity activity, ActivityPutRequest dto) {
        if (dto.name() != null) activity.setName(dto.name());
        if (dto.description() != null) activity.setDescription(dto.description());
        if (dto.status() != null) activity.setStatus(dto.status());
    }

    private void updateActivityFields(Activity activity, ActivityPostRequest request) {
        activity.setName(request.name());
        activity.setDescription(request.description());
        activity.setStartDate(request.startDate());
        activity.setEndDate(request.endDate());
        activity.setStatus(request.status());
    }
}
