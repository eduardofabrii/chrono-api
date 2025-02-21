package com.chrono.service.user;

import java.util.List;

import com.chrono.domain.user.UserRole;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;

public interface UserService {
    List<UserGetResponse> findAllUsers();
    List<UserGetResponse> findUserByName(String name);
    UserGetResponse findUserById(Integer id);
    UserPutResponse updateUser(Integer id, UserPutRequest dto);
    UserPostResponse saveUser(UserPostRequest postRequest);
    void deleteUserById(Long id);
    void updateLastLogin(String email);
    List<UserGetResponse> findUsersByRole(UserRole role);
}
