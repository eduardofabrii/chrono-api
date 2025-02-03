package com.chrono.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chrono.domain.activity.Activity;
import com.chrono.mapper.ActivityMapper;
import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.activity.ActivityPostResponse;
import com.chrono.response.activity.ActivityPutResponse;
import com.chrono.service.activity.ActivityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityMapper mapper;
    private final ActivityService activityService;

    // GET to list all activities
    @GetMapping
    public ResponseEntity<List<ActivityGetResponse>> listAll() {
        List<Activity> activities = activityService.findAllActivities();
        List<ActivityGetResponse> responseList = mapper.toActivityGetResponseList(activities);
        return ResponseEntity.ok(responseList);
    }

    // GET to find Activity by name
    @GetMapping("name")
    public ResponseEntity<List<ActivityGetResponse>> getActivityByName(@RequestParam String name) {
        List<Activity> activities = activityService.findActivityByName(name);
        List<ActivityGetResponse> response = mapper.toActivityGetResponseList(activities);
        return ResponseEntity.ok().body(response);
    }

    // GET to find Activity by id
    @GetMapping("{id}")
    public ResponseEntity<ActivityGetResponse> getActivityById(@PathVariable Integer id) {
        Activity activity = activityService.findActivityById(id);
        ActivityGetResponse response = mapper.toActivityGetResponse(activity);
        return ResponseEntity.ok().body(response);
    }

    // PUT to update Activity
    @PutMapping("{id}")
    public ResponseEntity<ActivityPutResponse> updateActivity(@Valid @RequestBody ActivityPutRequest dto, @PathVariable Integer id) {
        Activity activity = mapper.toActivityPut(dto);
        activity.setId(id);
        
        activityService.updateActivity(activity); 
        ActivityPutResponse response = mapper.toActivityPutResponse(activity);
        return ResponseEntity.ok().body(response);
    }

    // POST to save Activity
    @PostMapping
    public ResponseEntity<ActivityPostResponse> saveActivity(@Valid @RequestBody ActivityPostRequest postRequest) throws URISyntaxException {
        Activity activity = mapper.toActivityPost(postRequest);
        activityService.saveActivity(activity);
        
        ActivityPostResponse response = mapper.toActivityPostResponse(activity);
        return ResponseEntity.created(new URI("/v1/Activity/" + activity.getId())).body(response);
    }

    // DELETE to delete Activity
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable Long id) {
        activityService.deleteActivityById(id);
        return ResponseEntity.noContent().build();
    }
}