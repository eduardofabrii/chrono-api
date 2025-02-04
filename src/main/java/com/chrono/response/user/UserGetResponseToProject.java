package com.chrono.response.user;

public record UserGetResponseToProject(
    Integer id,
    String name,
    String email
) {}