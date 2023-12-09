package com.example.aups.controllers;

import com.example.aups.exceptions.UserDoesNotExistException;
import com.example.aups.models.ResetPasswordDto;
import com.example.aups.models.User;
import com.example.aups.models.UserDto;
import com.example.aups.security.CurrentSession;
import com.example.aups.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final CurrentSession currentSession;

    public UserController(UserService userService, CurrentSession currentSession) {
            this.userService = userService;
        this.currentSession = currentSession;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/technicians")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findUserById() {
        return ResponseEntity.ok(userService.getAllTechnicians());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        userService.update(id, userDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/reset-password")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public ResponseEntity<UserDto> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        Optional<String> username = currentSession.currentUserUsername();
        username.ifPresentOrElse(u ->
                        userService.resetPassword(u, resetPasswordDto)
                ,
                () -> {
                    throw new UserDoesNotExistException("");
                });
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto user) {
        return ResponseEntity.ok().body(userService.registerUser(user));
    }
}
