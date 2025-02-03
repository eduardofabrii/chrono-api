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

import com.chrono.domain.project.Project;
import com.chrono.mapper.ProjectMapper;
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

    private final ProjectMapper mapper;
    private final ProjectService projectService;

    // GET to list all projects
    @GetMapping
    public ResponseEntity<List<ProjectGetResponse>> listAll() {
        List<Project> projects = projectService.findAllProjects();
        List<ProjectGetResponse> responseList = mapper.toProjectGetResponseList(projects);
        return ResponseEntity.ok(responseList);
    }

    // GET to find project by name
    @GetMapping("name")
    public ResponseEntity<List<ProjectGetResponse>> getProjectByName(@RequestParam String name) {
        List<Project> projects = projectService.findProjectByName(name);
        List<ProjectGetResponse> response = mapper.toProjectGetResponseList(projects);
        return ResponseEntity.ok().body(response);
    }

    // GET to find project by id
    @GetMapping("{id}")
    public ResponseEntity<ProjectGetResponse> getProjectById(@PathVariable Integer id) {
        Project project = projectService.findProjectById(id);
        ProjectGetResponse response = mapper.toProjectGetResponse(project);
        return ResponseEntity.ok().body(response);
    }

    // PUT to update project
    @PutMapping("{id}")
    public ResponseEntity<ProjectPutResponse> updateProject(@Valid @RequestBody ProjectPutRequest dto, @PathVariable Integer id) {
        Project project = mapper.toProjectPut(dto);
        project.setId(id);
        
        projectService.updateProject(project);
        ProjectPutResponse response = mapper.toProjectPutResponse(project);
        return ResponseEntity.ok().body(response);
    }
    

    // POST to save project
    @PostMapping
    public ResponseEntity<ProjectPostResponse> saveProject(@Valid @RequestBody ProjectPostRequest postRequest) throws URISyntaxException {
        Project project = mapper.toProjectPost(postRequest);
        projectService.saveProject(project);
        
        ProjectPostResponse response = mapper.toProjectPostResponse(project);
        return ResponseEntity.created(new URI("/v1/project/" + project.getId())).body(response);
    }

    // DELETE to delete project
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}