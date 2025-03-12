package com.chrono.response.user;

import java.time.LocalDateTime;

import com.chrono.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;

public record UserGetResponse(
    Integer id,
    String name,
    String email,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime lastLogin,
    
    UserRole role,
    Boolean active
) {}