package com.project.exception;

public class ServiceException extends CustomException{
    public ServiceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
