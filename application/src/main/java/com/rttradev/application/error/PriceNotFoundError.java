package com.rttradev.application.error;


import lombok.Getter;

@Getter
public class PriceNotFoundError extends ApplicationException {

    public PriceNotFoundError(final String errorDescription, final Integer errorCode) {
        super(errorDescription, errorCode);
    }
}
