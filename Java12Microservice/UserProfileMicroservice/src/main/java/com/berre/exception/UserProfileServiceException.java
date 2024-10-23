package com.berre.exception;

import lombok.Getter;

@Getter
public class UserProfileServiceException extends RuntimeException{

    private final ErrorType errorType;

    public UserProfileServiceException(ErrorType errorType) {
        super(errorType.getMesaj());
        this.errorType = errorType;
    }

    public UserProfileServiceException(ErrorType errorType, String mesaj) {
        super(mesaj);
        this.errorType = errorType;
    }


}
