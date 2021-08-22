package com.j2kb.minipetpee.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handle(ServiceException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(exception.getMessage());
    }
}
