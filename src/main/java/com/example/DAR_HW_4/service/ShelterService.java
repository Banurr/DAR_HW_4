package com.example.DAR_HW_4.service;


import com.example.DAR_HW_4.enums.ShelterType;
import com.example.DAR_HW_4.exception.InvalidShelterTypeException;
import com.example.DAR_HW_4.exception.ShelterException;
import com.example.DAR_HW_4.model.QShelter;
import com.example.DAR_HW_4.model.Shelter;
import com.example.DAR_HW_4.model.ShelterFilter;
import com.example.DAR_HW_4.model.ShelterSort;
import com.example.DAR_HW_4.repository.ShelterRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final String EXCEPTION_MESSAGE = "Correct types are " + Arrays.toString(ShelterType.values());

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

    public Page<Shelter> getFilteredShelters(ShelterFilter shelterFilter, ShelterSort shelterSort, int page, int size) {

        QShelter qShelter = QShelter.shelter;
        BooleanBuilder filterPredicate = new BooleanBuilder();

        filterByAttributes(shelterFilter, filterPredicate, qShelter);

        filterDiapason(shelterFilter, filterPredicate, qShelter);

        Pageable pageable = PageRequest.of(page, size, getSortByAttributes(shelterSort));

        return shelterRepository.findAll(filterPredicate, pageable);
    }

    private void filterByAttributes(ShelterFilter shelterFilter, BooleanBuilder filterPredicate, QShelter qShelter) {

        if(isValid(shelterFilter.getName())) {
            filterPredicate.and(qShelter.name.containsIgnoreCase(shelterFilter.getName()));
        }

        if(isValid(shelterFilter.getType())) {
            try {
                filterPredicate.and(qShelter.type.eq(ShelterType.valueOf(shelterFilter.getType())));
            }
            catch (Exception e) {
                throw new InvalidShelterTypeException(EXCEPTION_MESSAGE);
            }

        }

        if(isValid(shelterFilter.getLocation())) {
            filterPredicate.and(qShelter.location.containsIgnoreCase(shelterFilter.getLocation()));
        }

    }

    private Sort getSortByAttributes(ShelterSort shelterSort) {

        Sort sort = Sort.unsorted();

        if(isValid(shelterSort.getSortByRating())) {
            sort = sort.and(Sort.by(Sort.Direction.fromString(shelterSort.getSortByRating()), "rating"));
        }

        if(isValid(shelterSort.getSortByCapacity())) {
            sort = sort.and(Sort.by(Sort.Direction.fromString(shelterSort.getSortByCapacity()), "capacity"));
        }

        if(isValid(shelterSort.getSortByAverageAdoptionTime())) {
            sort = sort.and(Sort.by(Sort.Direction.fromString(shelterSort.getSortByAverageAdoptionTime()), "averageAdoptionTime"));
        }

        return sort;
    }

    private void filterDiapason(ShelterFilter shelterFilter, BooleanBuilder filterPredicate, QShelter qShelter) {

        if(isValid(shelterFilter.getMinCapacity())) {
            filterPredicate.and(qShelter.capacity.goe(shelterFilter.getMinCapacity()));
        }

        if(isValid(shelterFilter.getMaxCapacity())) {
            filterPredicate.and(qShelter.capacity.loe(shelterFilter.getMaxCapacity()));
        }

        if(isValid(shelterFilter.getMinRating())) {
            filterPredicate.and(qShelter.rating.goe(shelterFilter.getMinRating()));
        }

        if(isValid(shelterFilter.getMaxRating())) {
            filterPredicate.and(qShelter.rating.loe(shelterFilter.getMaxRating()));
        }

        if(isValid(shelterFilter.getMinAverageAdoptionTime())) {
            filterPredicate.and(qShelter.averageAdoptionTime.goe(shelterFilter.getMinAverageAdoptionTime()));
        }

        if(isValid(shelterFilter.getMaxAverageAdoptionTime())) {
            filterPredicate.and(qShelter.averageAdoptionTime.loe(shelterFilter.getMaxAverageAdoptionTime()));
        }

        if(isValid(shelterFilter.getMinDailyCost())) {
            filterPredicate.and(qShelter.dailyCost.goe(shelterFilter.getMinDailyCost()));
        }

        if(isValid(shelterFilter.getMaxDailyCost())) {
            filterPredicate.and(qShelter.dailyCost.loe(shelterFilter.getMaxDailyCost()));
        }

    }

    private boolean isValid(Object attribute) {
        return attribute != null;
    }

}
