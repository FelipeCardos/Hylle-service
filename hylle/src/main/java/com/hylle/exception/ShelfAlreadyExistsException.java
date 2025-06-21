package com.hylle.exception;

public class ShelfAlreadyExistsException extends RuntimeException {
    public ShelfAlreadyExistsException(String message) {
        super(message);
    }
}
