package com.chrono.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserGetResponseToProject {
    private Integer id;
    private String name;
    private String email;
}