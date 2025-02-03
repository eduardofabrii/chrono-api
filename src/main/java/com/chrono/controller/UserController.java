package com.chrono.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chrono.domain.user.User;
import com.chrono.infra.security.util.PasswordUtil;
import com.chrono.mapper.UserMapper;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;
import com.chrono.service.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;
    private final UserService userService;

    // GET to all users
    @GetMapping
    public ResponseEntity<List<UserGetResponse>> listAll() {
        List<User> users = userService.findAllUsers();
        List<UserGetResponse> userGetResponseList = mapper.toUserGetResponseList(users);
        
        return ResponseEntity.ok(userGetResponseList);
    }

    // GET to find user by filter name 
    @GetMapping("name")
    public ResponseEntity<List<UserGetResponse>> getUserByName(@RequestParam String name) {
        List<User> users = userService.findUserByName(name);
        List<UserGetResponse> response = mapper.toUserGetResponseList(users);
        return ResponseEntity.ok().body(response);
    }

    // GET to find user by id
    @GetMapping("{id}")
    public ResponseEntity<UserGetResponse> getUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        UserGetResponse response = mapper.toUserGetResponse(user);
        return ResponseEntity.ok().body(response);
    }

    // PUT to update user
    @PutMapping("{id}")
    public ResponseEntity<UserPutResponse> updateUser(@Valid @RequestBody UserPutRequest dto, @PathVariable Integer id) {
        User user = mapper.toUserPut(dto);
        user.setId(id);
        userService.updateUser(user);
        UserPutResponse response = mapper.toUserPutResponse(user);
        return ResponseEntity.ok().body(response);
    }

    // POST to save user
    @PostMapping
    public ResponseEntity<UserPostResponse> saveUser(@Valid @RequestBody UserPostRequest postRequest) throws URISyntaxException {
        String hashPassword = PasswordUtil.encoder(postRequest.getPassword());
        postRequest.setPassword(hashPassword);

        User user = mapper.toUserPost(postRequest);
        userService.saveUser(user);

        UserPostResponse response = mapper.toUserPostResponse(user);

        return ResponseEntity.created(new URI("/v1/user/" + user.getId())).body(response);
    }

    // DELETE to delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws URISyntaxException {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}