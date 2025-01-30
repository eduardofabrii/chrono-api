package com.chrono.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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