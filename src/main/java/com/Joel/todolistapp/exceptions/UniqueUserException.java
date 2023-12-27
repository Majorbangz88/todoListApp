package com.Joel.todolistapp.exceptions;

public class UniqueUserException extends RuntimeException{

    public UniqueUserException(String message) {
        super(message);
    }
}
