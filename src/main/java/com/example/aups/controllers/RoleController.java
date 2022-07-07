package com.example.aups.controllers;

import com.example.aups.models.Role;
import com.example.aups.repositories.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository ) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
