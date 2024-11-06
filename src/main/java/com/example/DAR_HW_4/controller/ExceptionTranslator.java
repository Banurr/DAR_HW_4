package com.example.DAR_HW_4.controller;


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
        ErrorData errorData = new ErrorData(exception.getMessage(), LocalDate.now());
        return ResponseEntity.internalServerError().body(errorData);
    }
}
