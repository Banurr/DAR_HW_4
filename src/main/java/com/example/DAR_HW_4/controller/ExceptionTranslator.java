package com.example.DAR_HW_4.controller;


import com.example.DAR_HW_4.exception.InvalidShelterTypeException;
import com.example.DAR_HW_4.exception.ShelterException;
import com.example.DAR_HW_4.model.ErrorData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ShelterException.class)
    public ResponseEntity<ErrorData> handleShelterException(ShelterException exception) {
        return generateResponseError(exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorData> IllegalArgumentException(IllegalArgumentException exception) {
        return generateResponseError(exception);
    }

    @ExceptionHandler(InvalidShelterTypeException.class)
    public ResponseEntity<ErrorData> IllegalArgumentException(InvalidShelterTypeException exception) {
        return generateResponseError(exception);
    }

    private ResponseEntity<ErrorData> generateResponseError(Throwable exception) {
        ErrorData errorData = new ErrorData(exception.getMessage(), LocalDate.now());
        return ResponseEntity.internalServerError().body(errorData);
    }
}
