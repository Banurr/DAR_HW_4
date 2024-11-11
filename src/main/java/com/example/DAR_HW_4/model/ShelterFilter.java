package com.example.DAR_HW_4.model;

import lombok.Data;

@Data
public class ShelterFilter {

    private String name;

    private String type;

    private String location;

    private Long minCapacity;

    private Long maxCapacity;

    private Double minRating;

    private Double maxRating;

    private Double minAverageAdoptionTime;

    private Double maxAverageAdoptionTime;

    private Long minDailyCost;

    private Long maxDailyCost;
}
