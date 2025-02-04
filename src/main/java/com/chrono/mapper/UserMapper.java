package com.chrono.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.chrono.domain.user.User;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    User toUserPost(UserPostRequest userRequest);

    @Mapping(target = "id", ignore = true)
    User toUserPut(UserPutRequest userRequest);

    UserGetResponse toUserGetResponse(User user);

    UserPostResponse toUserPostResponse(User user);

    UserPutResponse toUserPutResponse(User user);

    List<UserGetResponse> toUserGetResponseList(List<User> users); 
}