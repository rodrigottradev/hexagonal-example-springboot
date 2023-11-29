package com.rttradev.application.error;


import lombok.Getter;

@Getter
public class ApplicationException extends Exception {

    private final transient ErrorMessage errorMessage;

    public ApplicationException(String errorDescription, Integer errorCode) {
        this.errorMessage = new ErrorMessage(errorDescription, errorCode);
    }


}
