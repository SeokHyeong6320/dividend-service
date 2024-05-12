package com.project.exception;

import com.project.domain.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResponse>
                handleServiceException(ServiceException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.fromErrorCode(e.getErrorCode()));
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ExceptionResponse>
                handleServerException(ServerException e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.fromErrorCode(e.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse>
                handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.fromException(e));
    }


}
