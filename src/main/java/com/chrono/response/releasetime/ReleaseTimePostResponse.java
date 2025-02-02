package com.chrono.response.releasetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.user.UserGetResponseToProject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReleaseTimePostResponse {
    private Integer id;
    private ActivityGetResponse activity;
    private UserGetResponseToProject user;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime registerDate;
}
