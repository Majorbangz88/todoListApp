package com.Joel.todolistapp.exceptions;

public class UsernameOrTaskNameNotFoundException extends RuntimeException {
    public UsernameOrTaskNameNotFoundException(String message) {
        super(message);
    }
}
