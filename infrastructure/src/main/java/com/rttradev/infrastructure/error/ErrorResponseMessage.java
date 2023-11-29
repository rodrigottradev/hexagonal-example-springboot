package com.rttradev.infrastructure.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseMessage {

    private HttpStatus httpStatus;
    private String errorDescription;


}
