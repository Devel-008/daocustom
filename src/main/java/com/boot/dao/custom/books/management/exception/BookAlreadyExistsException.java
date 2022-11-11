package com.boot.dao.custom.books.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookAlreadyExistsException extends RuntimeException {
    private String message;
    public BookAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
