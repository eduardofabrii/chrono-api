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

import com.chrono.request.project.ProjectPostRequest;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.project.ProjectPutResponse;
import com.chrono.service.project.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // GET to list all projects
    @GetMapping
    public ResponseEntity<List<ProjectGetResponse>> listAll() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    // GET to find user by project
    @GetMapping("name")
    public ResponseEntity<List<ProjectGetResponse>> getProjectByName(@RequestParam String name) {
        return ResponseEntity.ok(projectService.findProjectByName(name));
    }

    // GET to find project by id
    @GetMapping("{id}")
    public ResponseEntity<ProjectGetResponse> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    // POST to save project
    @PutMapping("{id}")
    public ResponseEntity<ProjectPutResponse> updateProject(@Valid @RequestBody ProjectPutRequest dto, @PathVariable Integer id) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    // POST to save project
    @PostMapping
    public ResponseEntity<ProjectPostResponse> saveProject(@Valid @RequestBody ProjectPostRequest postRequest) throws URISyntaxException {
        ProjectPostResponse response = projectService.saveProject(postRequest);
        return ResponseEntity.created(new URI("/v1/project/" + response.id())).body(response);
    }

    // DELETE to delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
