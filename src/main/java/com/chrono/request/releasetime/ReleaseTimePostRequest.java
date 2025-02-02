package com.chrono.request.releasetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.user.User;
import com.chrono.response.activity.ActivityGetResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReleaseTimePostRequest {
    private Integer id;
    private ActivityGetResponse activity;
    private User user;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}