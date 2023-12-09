package com.example.aups.services;

import com.example.aups.models.UserDto;
import com.example.aups.exceptions.CustomException;
import com.example.aups.exceptions.UserDoesNotExistException;
import com.example.aups.exceptions.UserWithEmailDoesNotExistException;
import com.example.aups.models.User;
import com.example.aups.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserDoesNotExistException(id));
    }

    @Transactional(readOnly = true)
    public List<User> getAllTechnicians() {
        return userRepository.findAllByRoleName("ROLE_TECHNICIAN");
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresentOrElse(u -> {
                    u.setFirstName(userDto.getFirstName());
                    u.setSurname(userDto.getSurname());
                    u.setEmail(userDto.getEmail());
                    u.setRole(roleService.getRoleById(userDto.getRoleId()));
                    userRepository.save(u);
                },
                () -> {
                throw new UserDoesNotExistException(id);
            });

        return userDto;
    }

    @Transactional
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserDoesNotExistException(id);
        }
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserWithEmailDoesNotExistException(email));
    }

    @Transactional(readOnly = true)
    public void validateEmail(String email){
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException("Email is already in use.");
        }
    }
}
