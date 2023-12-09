package com.example.aups.exceptions;

public class SessionNotFoundException extends  RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}


