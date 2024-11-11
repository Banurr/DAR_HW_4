package com.example.DAR_HW_4.controller;


import com.example.DAR_HW_4.model.Shelter;
import com.example.DAR_HW_4.model.ShelterFilter;
import com.example.DAR_HW_4.model.ShelterSort;
import com.example.DAR_HW_4.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/shelters")
@RequiredArgsConstructor
public class ShelterController {

    private final ShelterService shelterService;

    @PostMapping
    public void createShelter(@RequestBody Shelter shelter) {
        shelterService.createShelter(shelter);
    }

    @GetMapping("/all")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @GetMapping("/{id}")
    public Shelter getShelterById(@PathVariable String id) {
        return shelterService.getShelterById(id);
    }

    @PutMapping
    public void updateShelter(@RequestBody Shelter shelter) {
        shelterService.updateShelter(shelter);
    }

    @DeleteMapping("/{id}")
    public void deleteShelter(@PathVariable String id) {
        shelterService.deleteShelterById(id);
    }

    @GetMapping("/filter")
    public List<Shelter> getFilteredShelters(Object s,
                                             @ModelAttribute ShelterFilter shelterFilter,
                                             @ModelAttribute ShelterSort shelterSort,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size
    ) {

        return shelterService.getFilteredShelters(shelterFilter, shelterSort, page, size).getContent();
    }
}
