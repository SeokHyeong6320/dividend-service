package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthException extends CustomException{

    private String message;

    public AuthException(String message) {
        super(message);
        this.message = message;
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
