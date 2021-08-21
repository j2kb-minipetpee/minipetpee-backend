package com.j2kb.minipetpee.global.exception;

import com.j2kb.minipetpee.global.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public ServiceException(HttpStatus status, ErrorCode errorCode) {
        this.status = status;
        this.message = errorCode.getMessage();
    }
}
