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
import org.springframework.web.bind.annotation.RestController;

import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;
import com.chrono.service.releasetime.ReleaseTimeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/hours")
@RequiredArgsConstructor
public class ReleaseTimeController {

    private final ReleaseTimeService releaseTimeService;

    // GET to list all release times
    @GetMapping
    public ResponseEntity<List<ReleaseTimeGetResponse>> listAll() {
        return ResponseEntity.ok(releaseTimeService.findAllReleases());
    }

    // GET to find release time by ID
    @GetMapping("{id}")
    public ResponseEntity<ReleaseTimeGetResponse> getReleaseTimeById(@PathVariable Integer id) {
        return ResponseEntity.ok(releaseTimeService.findReleaseTimeById(id));
    }

    // PUT to update release time
    @PutMapping("{id}")
    public ResponseEntity<ReleaseTimePutResponse> updateReleaseTime(@Valid @RequestBody ReleaseTimePutRequest dto, @PathVariable Integer id) {
        return ResponseEntity.ok(releaseTimeService.updateReleaseTime(dto, id));
    }

    // POST to save release time
    @PostMapping
    public ResponseEntity<ReleaseTimePostResponse> saveReleaseTime(@Valid @RequestBody ReleaseTimePostRequest postRequest) throws URISyntaxException {
        ReleaseTimePostResponse response = releaseTimeService.saveReleaseTime(postRequest);
        return ResponseEntity.created(new URI("/v1/hours/" + response.id())).body(response);
    }

    // DELETE to delete release time
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReleaseTimeById(@PathVariable Long id) {
        releaseTimeService.deleteReleaseTimeById(id);
        return ResponseEntity.noContent().build();
    }
}
