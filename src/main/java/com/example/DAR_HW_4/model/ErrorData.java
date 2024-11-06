package com.example.DAR_HW_4.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorData {

    private String message;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
}
