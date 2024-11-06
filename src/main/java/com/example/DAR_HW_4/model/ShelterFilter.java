package com.example.DAR_HW_4.model;

import com.example.DAR_HW_4.enums.ShelterType;
import lombok.Data;

@Data
public class ShelterFilter {

    private String name;
    private ShelterType type;
    private String location;
}
