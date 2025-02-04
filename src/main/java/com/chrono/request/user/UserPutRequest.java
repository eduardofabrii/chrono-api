package com.chrono.request.user;

import com.chrono.domain.user.UserRole;

public record UserPutRequest(
    Integer id,
    String name,
    String email,
    String password,
    UserRole role
) {}
