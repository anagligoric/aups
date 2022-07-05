package com.example.aups.controllers;

import com.example.aups.models.Alat;
import com.example.aups.models.Role;
import com.example.aups.repositories.AlatRepository;
import com.example.aups.repositories.RoleRepository;
import com.example.aups.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final AlatRepository alatRepository;

    public RoleController(RoleRepository roleRepository, RoleService roleService, AlatRepository alatRepository) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.alatRepository = alatRepository;
    }

    @GetMapping("/alat")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @PostMapping("/alat")
    public void insertAlat(@RequestBody Alat alat) {
        alatRepository.save(alat);
    }
}
