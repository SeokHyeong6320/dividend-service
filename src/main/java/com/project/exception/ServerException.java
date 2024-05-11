package com.project.exception;

public class ServerException extends CustomException{

    public ServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
