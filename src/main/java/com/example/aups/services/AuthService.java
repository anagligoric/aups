package com.example.aups.services;

import com.example.aups.models.UserDto;
import com.example.aups.exceptions.CustomException;
import com.example.aups.models.LoginCredentials;
import com.example.aups.models.MailHeader;
import com.example.aups.models.User;
import com.example.aups.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final RoleService roleService;

    public AuthService(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder, EmailService emailService, RoleService roleService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.roleService = roleService;
    }

    @Transactional
    public Map<String, Object> login(LoginCredentials body) {
        try {
            User user = userService.findByEmail(body.getEmail());
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getEmail(), user.getRole().getName());
            return Collections.singletonMap("token", token);
        } catch (AuthenticationException authExc) {
            throw new CustomException("Invalid login credentials");
        }
    }

    @Transactional
    public UserDto registerUser(UserDto userDto) {
        userService.validateEmail(userDto.getEmail());
        String randomPassword = RandomPasswordGenerator.generatePassword(12);
        String encodedPass = passwordEncoder.encode(randomPassword);
        User user = new User(userDto.getFirstName(), userDto.getSurname(), userDto.getEmail(), encodedPass, roleService.getRoleById(userDto.getRoleId()));
        userService.create(user);
        emailService.sendTextEmailNoAttachment(new MailHeader(userDto.getEmail(), "tools@service.com", "New Account"),
                "New account has been created for user ".concat( userDto.getEmail()).concat(" with password ").concat(randomPassword).concat("\n")
                        .concat("Please visit Tools service app and login"));
        return userDto;
    }
}
