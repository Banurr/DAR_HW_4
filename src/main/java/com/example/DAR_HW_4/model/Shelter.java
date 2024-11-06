package com.example.DAR_HW_4.model;

import com.example.DAR_HW_4.enums.ShelterType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@RequiredArgsConstructor
@Document("shelters")
@Data
public class Shelter implements Serializable {

    @Id
    private String id;

    private String name;

    private String location;

    private Long capacity;

    private ShelterType type;

    private Double rating;

    private Boolean isGovernmentFunded;

    private Double averageAdoptionTime;

    private Long dailyCost;
}
