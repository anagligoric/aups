package com.example.aups.services;

import com.example.aups.models.MailHeader;
import com.example.aups.models.ResetPasswordDto;
import com.example.aups.models.UserDto;
import com.example.aups.exceptions.CustomException;
import com.example.aups.exceptions.UserDoesNotExistException;
import com.example.aups.exceptions.UserWithEmailDoesNotExistException;
import com.example.aups.models.User;
import com.example.aups.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
    public List<UserDto> getAllTechnicians() {
        return userRepository.findAllByRoleName("ROLE_TECHNICIAN").stream()
                .map(technician -> new UserDto(technician.getId(),
                        technician.getFirstName(),
                        technician.getSurname(),
                        technician.getEmail(),
                        technician.getRole().getId())).toList();
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
    public UserDto registerUser(UserDto userDto) {
        validateEmail(userDto.getEmail());
        String randomPassword = RandomPasswordGenerator.generatePassword(12);
        String encodedPass = passwordEncoder.encode(randomPassword);
        User user = new User(userDto.getFirstName(), userDto.getSurname(), userDto.getEmail(), encodedPass, roleService.getRoleById(userDto.getRoleId()));
        userRepository.save(user);
        emailService.sendTextEmailNoAttachment(new MailHeader(userDto.getEmail(), "tools@service.com", "New Account"),
                "New account has been created for user ".concat( userDto.getEmail()).concat(" with password ").concat(randomPassword).concat("\n")
                        .concat("Please visit Tools service app and login"));
        return userDto;
    }

    @Transactional
    public void resetPassword(String email, ResetPasswordDto resetPasswordDto){
        if (!Objects.equals(email, resetPasswordDto.getUsername())) {
            throw new CustomException("You are not allowed to change password for this user.");
        }

        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresentOrElse(u -> {
                    String encodedNewPass = passwordEncoder.encode(resetPasswordDto.getNewPassword());
                    if (!passwordEncoder.matches(resetPasswordDto.getOldPassword(), u.getPassword())) {
                        throw new CustomException("Old password does not match.");
                    }
                    u.setPassword(encodedNewPass);
                    userRepository.save(u);
                },
                () -> {
                    throw new UserDoesNotExistException(email);
                });

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
