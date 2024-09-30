package com.jeremias.shortenerurl.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StandardErrorResponse {
    private HttpStatus status;
    private String message;
}
