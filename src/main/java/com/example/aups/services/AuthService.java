package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.LoginCredentials;
import com.example.aups.models.User;
import com.example.aups.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder){
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Map<String, Object> login(LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authManager.authenticate(authInputToken);
            User user = userService.findByEmail(body.getEmail());
            String token = jwtUtil.generateToken(body.getEmail(), user.getRole().getIme());
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new CustomException("Invalid login credentials");
        }
    }

    @Transactional
    public Map<String, Object> register(User user) {
        userService.validateEmail(user.getEmail());
        String encodedPass = passwordEncoder.encode(user.getLozinka());
        user.setLozinka(encodedPass);
        user = userService.create(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().getIme());
        return Collections.singletonMap("jwt-token", token);
    }

    @Transactional
    public Map<String, Object> registerUser(User user) {
        userService.validateEmail(user.getEmail());
        String encodedPass = passwordEncoder.encode(user.getLozinka());
        user.setLozinka(encodedPass);
        if(user.getRole().getIme().equals("ROLE_ADMIN")){
            throw new CustomException("You are not authorized to register as admin.");
        }
        user.setRole(roleService.getRoleById(2L));
        user = userService.create(user);
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().getIme());
        return Collections.singletonMap("jwt-token", token);
    }
}
