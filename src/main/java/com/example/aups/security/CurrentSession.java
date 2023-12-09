package com.example.aups.security;

import com.example.aups.exceptions.SessionNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentSession {
    private final MyUserDetailsService myUserDetailsService;

    public CurrentSession(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    public Optional<String> currentUserUsername() {
        return userSecurityDetails().map(UserDetails::getUsername);
    }

    public Optional<UserDetails> userSecurityDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> userSecurityDetails(authentication.getName()))
                .orElseThrow(() -> new SessionNotFoundException("Could not find current session"));
    }

    public Optional<UserDetails> userSecurityDetails(String username) {
        return Optional.of(myUserDetailsService.loadUserByUsername(username));
    }
}
