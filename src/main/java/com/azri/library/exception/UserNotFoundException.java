package com.azri.library.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("Not found Book with id:" + id + ".");
    }
}