package com.boot.dao.custom.books.management.exception;

public class BookAlreadyExistsException extends RuntimeException {
    private String message;
    public BookAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
