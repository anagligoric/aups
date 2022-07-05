package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.models.Role;
import com.example.aups.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with" + id + "does not exist."));
    }
}
