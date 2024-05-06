package com.project.exception;

import com.project.domain.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResult> handleCustomException(CustomException e) {
        ExceptionResult exceptionResult = new ExceptionResult
                ("HttpStatus.BAD_REQUEST", e.getMessage());

        return new ResponseEntity<>
                (exceptionResult, HttpStatus.resolve(e.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResult> handleException(Exception e) {
        ExceptionResult exceptionResult = new ExceptionResult
                ("HttpStatus.INTERNAL_SERVER_ERROR", e.getMessage());

        return new ResponseEntity<>
                (exceptionResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
