package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.LoginCredentials;
import com.example.aups.models.User;
import com.example.aups.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthService(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Transactional
    public Map<String, Object> login(LoginCredentials body) {
        try {
            User user = userService.findByEmail(body.getEmail());
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getEmail(), user.getRole().getName(), user.getFirstName(), user.getSurname());
            return Collections.singletonMap("token", token);
        } catch (AuthenticationException authExc) {
            throw new CustomException("Invalid login credentials");
        }
    }
}
