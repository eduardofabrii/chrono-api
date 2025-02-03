package com.chrono.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReleaseTimeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "activity", source = "activity")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    ReleaseTime toReleaseTimePost(ReleaseTimePostRequest postRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "activity", source = "activity")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    ReleaseTime toReleaseTimePut(ReleaseTimePutRequest putRequest);

    ReleaseTimeGetResponse toReleaseTimeGetResponse(ReleaseTime releaseTime);

    ReleaseTimePostResponse toReleaseTimePostResponse(ReleaseTime releaseTime);

    ReleaseTimePutResponse toReleaseTimePutResponse(ReleaseTime releaseTime);

    List<ReleaseTimeGetResponse> toReleaseTimeGetResponseList(List<ReleaseTime> releaseTime); 
}
