package com.chrono.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chrono.domain.user.User;
import com.chrono.infra.security.dto.AuthenticationDTO;
import com.chrono.infra.security.dto.LoginResponseDTO;
import com.chrono.service.security.TokenService;
import com.chrono.service.user.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    // POST to send token authentication by name and password
    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var userPassword = new UsernamePasswordAuthenticationToken(dto.name(), dto.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        userService.updateLastLogin(dto.email());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}