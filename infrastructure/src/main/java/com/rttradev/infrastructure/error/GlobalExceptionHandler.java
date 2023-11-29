package com.rttradev.infrastructure.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rttradev.application.error.PriceNotFoundError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundError.class)
    protected ResponseEntity<ErrorResponseMessage> handleDomainGeneratedException(PriceNotFoundError exception) {
        ErrorResponseMessage message = new ErrorResponseMessage(
            HttpStatus.valueOf(exception.getErrorMessage().getErrorCode()),
            exception.getErrorMessage().getErrorDescription());
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorMessage> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ValidationErrorMessage validationErrorMessage = new ValidationErrorMessage(errors);
        return new ResponseEntity<>(validationErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
