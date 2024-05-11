package com.project.domain;

import com.project.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {
    private String errorCode;
    private String message;

    public static ExceptionResponse fromErrorCode(ErrorCode errorCode) {

        return new ExceptionResponse
                (errorCode.toString(), errorCode.getMessage());

    }

    public static ExceptionResponse fromException(Exception e) {
        return new ExceptionResponse(e.getClass().getName(), e.getMessage());
    }
}
