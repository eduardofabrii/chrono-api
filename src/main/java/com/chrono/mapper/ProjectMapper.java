package com.chrono.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.chrono.domain.project.Project;
import com.chrono.request.project.ProjectPostRequest;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.project.ProjectPutResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {

    @Mapping(target = "id", ignore = true)
    Project toProjectPost(ProjectPostRequest postRequest);

    @Mapping(target = "id", ignore = true)
    Project toProjectPut(ProjectPutRequest putRequest);

    ProjectPutResponse toProjectPutResponse(Project project);

    ProjectGetResponse toProjectGetResponse(Project project);

    ProjectPostResponse toProjectPostResponse(Project project);

    List<ProjectGetResponse> toProjectGetResponseList(List<Project> projects);
}
