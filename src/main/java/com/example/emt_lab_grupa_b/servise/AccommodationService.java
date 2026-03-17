package com.example.emt_lab_grupa_b.servise;

import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(AccommodationDto accommodationDto);
    Optional<Accommodation> update(Long id, AccommodationDto accommodationDto);
    void deleteById(Long id);
    Optional<Accommodation> markAsRented(Long id);
}
