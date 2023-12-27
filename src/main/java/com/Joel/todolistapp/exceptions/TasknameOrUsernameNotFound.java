package com.Joel.todolistapp.exceptions;

public class TasknameOrUsernameNotFound extends RuntimeException {
    public TasknameOrUsernameNotFound(String message) {
        super(message);
    }
}
