package com.chrono.request.user;

import com.chrono.domain.user.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPostRequest {
    private Integer id;

    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, max = 70)
    private String name;

    @NotBlank(message = "O email está vazio")
    private String email;

    @NotBlank(message = "A senha está vazio")
    private String password;

    private UserRole role;
}