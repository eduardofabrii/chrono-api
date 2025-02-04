package com.chrono.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.user.User;
import com.chrono.mapper.UserMapper;
import com.chrono.repository.UserRepository;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    // GET all users
    @Override
    public List<UserGetResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return mapper.toUserGetResponseList(users);
    }

    // GET user by name
    @Override
    public List<UserGetResponse> findUserByName(String name) {
        List<User> users = userRepository.findByNameContaining(name);
        return mapper.toUserGetResponseList(users);
    }

    // GET user by ID
    @Override
    public UserGetResponse findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toUserGetResponse(user);
    }

    // PUT to update user
    @Override
    public UserPutResponse updateUser(Integer id, UserPutRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.name() != null) user.setName(dto.name());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.password() != null) user.setPassword(dto.password());
        if (dto.role() != null) user.setRole(dto.role());

        userRepository.save(user);
        return mapper.toUserPutResponse(user);
    }

    // POST to save new user
    @Override
    public UserPostResponse saveUser(UserPostRequest postRequest) {
        User user = mapper.toUserPost(postRequest);
        userRepository.save(user);
        return mapper.toUserPostResponse(user);
    }

    // DELETE user by ID
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // UPDATE last login for user
    @Override
    public void updateLastLogin(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }
}
