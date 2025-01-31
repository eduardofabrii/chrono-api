package com.chrono.response.user;

import com.chrono.domain.user.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPostResponse {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}