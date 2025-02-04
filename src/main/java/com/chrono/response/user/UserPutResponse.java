package com.chrono.response.user;

import com.chrono.domain.user.UserRole;

public record UserPutResponse(
    Integer id,
    String name,
    String email,
    UserRole role
) {}