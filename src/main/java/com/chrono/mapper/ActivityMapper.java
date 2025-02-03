package com.chrono.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.chrono.domain.activity.Activity;
import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.activity.ActivityPostResponse;
import com.chrono.response.activity.ActivityPutResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "project", source = "project")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "responsible", source = "responsible")
    @Mapping(target = "creationDate", source = "creationDate")
    Activity toActivityPost(ActivityPostRequest postRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "project", source = "project")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "responsible", source = "responsible")
    @Mapping(target = "creationDate", source = "creationDate")
    Activity toActivityPut(ActivityPutRequest putRequest);

    ActivityGetResponse toActivityGetResponse(Activity activity);

    ActivityPostResponse toActivityPostResponse(Activity activity);

    ActivityPutResponse toActivityPutResponse(Activity activity);

    List<ActivityGetResponse> toActivityGetResponseList(List<Activity> activities); 
}