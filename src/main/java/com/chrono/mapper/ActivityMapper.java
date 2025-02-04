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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Activity toActivityPost(ActivityPostRequest postRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Activity toActivityPut(ActivityPutRequest putRequest);

    ActivityGetResponse toActivityGetResponse(Activity activity);

    ActivityPostResponse toActivityPostResponse(Activity activity);

    ActivityPutResponse toActivityPutResponse(Activity activity);

    List<ActivityGetResponse> toActivityGetResponseList(List<Activity> activities); 
}