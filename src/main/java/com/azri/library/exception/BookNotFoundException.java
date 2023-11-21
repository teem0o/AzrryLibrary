package com.azri.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String id) {
        super("Not found Book with id:" + id + ".");
    }
}
