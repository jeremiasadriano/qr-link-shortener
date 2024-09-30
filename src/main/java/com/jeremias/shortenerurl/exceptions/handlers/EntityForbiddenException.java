package com.jeremias.shortenerurl.exceptions.handlers;

public class EntityForbiddenException extends RuntimeException {
    public EntityForbiddenException(String message) {
        super(message);
    }
}
