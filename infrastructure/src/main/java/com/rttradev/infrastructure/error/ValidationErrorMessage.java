package com.rttradev.infrastructure.error;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Error message sent to the client when there are validation errors, such as mandatory element that was not sent in the request.
 */
@Data
public class ValidationErrorMessage {

    private HttpStatus httpStatus;
    private String errorDescription;
    private List<String> jsonErrors;

    public ValidationErrorMessage(List<String> jsonErrors) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorDescription = "Validation errors";
        this.jsonErrors = jsonErrors;
    }
}
