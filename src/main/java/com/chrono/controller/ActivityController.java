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

    private final ActivityService activityService;

    // GET to list all activities
    @GetMapping
    public ResponseEntity<List<ActivityGetResponse>> listAll() {
        return ResponseEntity.ok(activityService.findAllActivities());
    }

    // GET to find activity by name
    @GetMapping("name")
    public ResponseEntity<List<ActivityGetResponse>> getActivityByName(@RequestParam String name) {
        return ResponseEntity.ok(activityService.findActivityByName(name));
    }

    // GET to find activity by id
    @GetMapping("{id}")
    public ResponseEntity<ActivityGetResponse> getActivityById(@PathVariable Integer id) {
        return ResponseEntity.ok(activityService.findActivityById(id));
    }

    // PUT to update activity
    @PutMapping("{id}")
    public ResponseEntity<ActivityPutResponse> updateActivity(@Valid @RequestBody ActivityPutRequest dto, @PathVariable Integer id) {
        return ResponseEntity.ok(activityService.updateActivity(id, dto));
    }

    // POST to save activity
    @PostMapping
    public ResponseEntity<ActivityPostResponse> saveActivity(@Valid @RequestBody ActivityPostRequest postRequest) throws URISyntaxException {
        ActivityPostResponse response = activityService.saveActivity(postRequest);
        return ResponseEntity.created(new URI("/v1/activity/" + response.getId())).body(response);
    }

    // DELETE to delete activity
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable Long id) {
        activityService.deleteActivityById(id);
        return ResponseEntity.noContent().build();
    }
}
