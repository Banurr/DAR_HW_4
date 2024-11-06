package com.example.DAR_HW_4.service;


import com.example.DAR_HW_4.exception.ShelterException;
import com.example.DAR_HW_4.model.QShelter;
import com.example.DAR_HW_4.model.Shelter;
import com.example.DAR_HW_4.model.ShelterFilter;
import com.example.DAR_HW_4.repository.ShelterRepository;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;

    public void createShelter(Shelter shelter) {
        shelter.setId(String.valueOf(UUID.randomUUID()));
        shelterRepository.save(shelter);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public Shelter getShelterById(String id) {
        return shelterRepository.findById(id).orElseThrow(()-> new ShelterException("No shelter with id = " + id));
    }

    public void updateShelter(Shelter shelter) {
        shelterRepository.save(shelter);
    }

    public void deleteShelterById(String id) {
        getShelterById(id);
        shelterRepository.deleteById(id);
    }

    public List<Shelter> getFilteredShelters(ShelterFilter shelterFilter) {
        QShelter qShelter = QShelter.shelter;
        BooleanBuilder filterPredicate = new BooleanBuilder();
        if(shelterFilter.getName() != null) {
            filterPredicate.and(qShelter.name.containsIgnoreCase(shelterFilter.getName()));
        }
        if(shelterFilter.getType() != null) {
            filterPredicate.and(qShelter.type.eq(shelterFilter.getType()));
        }
        if(shelterFilter.getLocation() != null) {
            filterPredicate.and(qShelter.location.containsIgnoreCase(shelterFilter.getLocation()));
        }
        return null;
    }
}
