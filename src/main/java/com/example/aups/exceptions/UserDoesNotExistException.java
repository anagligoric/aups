package com.example.aups.exceptions;

public class UserDoesNotExistException extends  RuntimeException {
    public UserDoesNotExistException(Long id) {
        super("User with id: " + id + " does not exist");
    }
}
