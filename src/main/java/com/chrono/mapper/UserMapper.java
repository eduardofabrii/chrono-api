package com.chrono.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.chrono.domain.user.User;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    @Mapping(target = "id")
    @Mapping(target = "name")
    @Mapping(target = "email")
    @Mapping(target = "role")
    User toUserPost(UserPostRequest userRequest);

    @Mapping(target = "id")
    @Mapping(target = "name")
    @Mapping(target = "email")
    @Mapping(target = "role")
    User toUserPut(UserPutRequest userRequest);

    UserGetResponse toUserGetResponse(User user);

    UserPostResponse toUserPostResponse(User user);

    UserPutResponse toUserPutResponse(User user);

    List<UserGetResponse> toUserGetResponseList(List<User> users); 
}