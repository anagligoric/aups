package com.example.aups.database;

import com.example.aups.models.Role;
import com.example.aups.models.User;
import com.example.aups.services.RoleService;
import com.example.aups.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userService.getAllUsers().isEmpty()) {
            return;
        }
        ROLES.forEach(roleService::create);
        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.create(user);
        });
    }

    private static final List<Role> ROLES = Arrays.asList(
            new Role(1L, "ROLE_ADMIN"),
            new Role(2L, "ROLE_TECHNICIAN")
    );

    private static final List<User> USERS = Arrays.asList(
            new User(1L, "admin", "Admin", "admin@mycompany.com", "admin", ROLES.get(0)),
            new User(2L, "user", "User", "user@mycompany.com", "user", ROLES.get(1))
    );

}
