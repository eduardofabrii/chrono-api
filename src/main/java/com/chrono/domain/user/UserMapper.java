package com.chrono.domain.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User userToUserDTO(UserDTO dto) {
        return new User(null, dto.getName(), dto.getEmail(), dto.getPassword(), null, null, dto.getRole());
    }
}