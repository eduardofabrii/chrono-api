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

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.mapper.ReleaseTimeMapper;
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

    private final ReleaseTimeMapper mapper;
    private final ReleaseTimeService releaseTimeService;

    // GET to list all releases
    @GetMapping
    public ResponseEntity<List<ReleaseTimeGetResponse>> listAll() {
        List<ReleaseTime> releases = releaseTimeService.findAllReleases();
        List<ReleaseTimeGetResponse> responseList = mapper.toReleaseTimeGetResponseList(releases);
        return ResponseEntity.ok(responseList);
    }

    // GET to find ReleaseTime by id
    @GetMapping("{id}")
    public ResponseEntity<ReleaseTimeGetResponse> getReleaseTimeById(@PathVariable Integer id) {
        ReleaseTime releaseTime = releaseTimeService.findReleaseTimeById(id);
        ReleaseTimeGetResponse response = mapper.toReleaseTimeGetResponse(releaseTime);
        return ResponseEntity.ok().body(response);
    }

    // PUT to update releaseTime
    @PutMapping("{id}")
    public ResponseEntity<ReleaseTimePutResponse> updateReleaseTime(@Valid @RequestBody ReleaseTimePutRequest dto, @PathVariable Integer id) {
        ReleaseTime releaseTime = mapper.toReleaseTimePut(dto);
        releaseTime.setId(id);
        
        releaseTimeService.updateReleaseTime(releaseTime); 
        ReleaseTimePutResponse response = mapper.toReleaseTimePutResponse(releaseTime);
        return ResponseEntity.ok().body(response);
    }
    

    // POST to save releaseTime
    @PostMapping
    public ResponseEntity<ReleaseTimePostResponse> saveReleaseTime(@Valid @RequestBody ReleaseTimePostRequest postRequest) throws URISyntaxException {
        ReleaseTime releaseTime = mapper.toReleaseTimePost(postRequest);
        releaseTimeService.saveReleaseTime(releaseTime);
        
        ReleaseTimePostResponse response = mapper.toReleaseTimePostResponse(releaseTime);
        return ResponseEntity.created(new URI("/v1/ReleaseTime/" + releaseTime.getId())).body(response);
    }

    // DELETE to delete releaseTime
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReleaseTimeById(@PathVariable Long id) {
        releaseTimeService.deleteReleaseTimeById(id);
        return ResponseEntity.noContent().build();
    }
}