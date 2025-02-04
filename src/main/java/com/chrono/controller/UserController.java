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

    private final UserService userService;

    // GET to list all users
    @GetMapping
    public ResponseEntity<List<UserGetResponse>> listAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // GET to find user by name
    @GetMapping("name")
    public ResponseEntity<List<UserGetResponse>> getUserByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }

    // GET to find user by id
    @GetMapping("{id}")
    public ResponseEntity<UserGetResponse> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    // POST to save user
    @PutMapping("{id}")
    public ResponseEntity<UserPutResponse> updateUser(@Valid @RequestBody UserPutRequest dto, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // POST to save user
    @PostMapping
    public ResponseEntity<UserPostResponse> saveUser(@Valid @RequestBody UserPostRequest postRequest) throws URISyntaxException {
        UserPostResponse response = userService.saveUser(postRequest);
        return ResponseEntity.created(new URI("/v1/user/" + response.getId())).body(response);
    }

    // DELETE to delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
