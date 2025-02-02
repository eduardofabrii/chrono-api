package com.chrono.request.releasetime;

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
public class ReleaseTimePutRequest {
    private Integer id;
    private ActivityGetResponse activity;
    private UserGetResponseToProject user;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
