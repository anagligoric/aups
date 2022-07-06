package com.example.aups.controllers;

import com.example.aups.models.LoginCredentials;
import com.example.aups.models.User;
import com.example.aups.services.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/registeruser")
    public Map<String, Object> registerUser(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginCredentials body) {
        return authService.login(body);
    }
}