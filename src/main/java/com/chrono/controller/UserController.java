package com.chrono.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.chrono.domain.user.UserDTO;
import com.chrono.domain.user.UserMapper;
import com.chrono.service.user.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    // GET to list all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    // GET to find user by name
    @GetMapping("/name")
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {
        List<User> user = userService.findUserByName(name);
        return ResponseEntity.ok().body(user);
    }

    // GET to find user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = (User) userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    // PUT to update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO dto, @PathVariable Integer id) {
        User newUser = userMapper.userToUserDTO(dto);
        newUser.setId(id);
        userService.updateUser(newUser);
        return ResponseEntity.noContent().build();
    }

    // POST to save user
    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO dto) throws URISyntaxException {
        User newUser = userService.saveUser(userMapper.userToUserDTO(dto));

        return ResponseEntity.created(new URI("/user/save/" + newUser.getId())).body(newUser);
    }

    // DELETE to delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws URISyntaxException {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}