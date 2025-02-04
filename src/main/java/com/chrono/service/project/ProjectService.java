package com.chrono.service.project;

import java.util.List;

import com.chrono.request.project.ProjectPostRequest;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.project.ProjectPutResponse;

public interface ProjectService {
    List<ProjectGetResponse> findAllProjects();
    List<ProjectGetResponse> findProjectByName(String name);
    ProjectGetResponse findProjectById(Integer id);
    ProjectPutResponse updateProject(Integer id, ProjectPutRequest dto);
    ProjectPostResponse saveProject(ProjectPostRequest postRequest);
    void deleteProjectById(Long id);
}
