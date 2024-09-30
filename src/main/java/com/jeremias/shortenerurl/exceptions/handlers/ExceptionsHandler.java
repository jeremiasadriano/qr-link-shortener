package com.jeremias.shortenerurl.exceptions.handlers;

import com.jeremias.shortenerurl.exceptions.StandardErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    private final StandardErrorResponse errorResponse;

    @ExceptionHandler(EntityBadRequestException.class)
    public ResponseEntity<StandardErrorResponse> badRequest(EntityBadRequestException exception) {
        errorResponse.setStatus(BAD_REQUEST);
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> notfound(EntityNotFoundException exception) {
        errorResponse.setStatus(NOT_FOUND);
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(EntityForbiddenException.class)
    public ResponseEntity<StandardErrorResponse> forbidden(EntityForbiddenException exception) {
        errorResponse.setStatus(FORBIDDEN);
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, FORBIDDEN);
    }
}
