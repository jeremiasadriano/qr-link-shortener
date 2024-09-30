package com.jeremias.shortenerurl.exceptions.handlers;

public class EntityBadRequestException extends RuntimeException {
    public EntityBadRequestException(String message) {
        super(message);
    }
}
