package com.example.aups.exceptions;

public class TextEmailException extends  RuntimeException {
    public TextEmailException() {
        super("Error occurred while attempting to prepare or send text email.");
    }
}