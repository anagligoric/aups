package com.example.aups.exceptions;

public class UserWithEmailDoesNotExistException extends  RuntimeException {

    public UserWithEmailDoesNotExistException(String email ) {
        super("User with email: " + email + " does not exist");
    }
}
