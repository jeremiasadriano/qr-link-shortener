package com.jeremias.shortenerurl.exceptions.handlers;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String error) {
        super(error);
    }
}
