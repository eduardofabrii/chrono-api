package com.chrono.request.user;

import com.chrono.domain.user.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPutRequest {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
