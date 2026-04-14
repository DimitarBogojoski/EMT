package com.example.emt_lab_grupa_b.servise;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import com.example.emt_lab_grupa_b.model.dto.AccommodationCreateUpdateDto;
import com.example.emt_lab_grupa_b.model.dto.AccommodationDetailsDto;
import com.example.emt_lab_grupa_b.model.dto.AccommodationShortDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<AccommodationShortDto> findAll();
    Optional<AccommodationDetailsDto> findById(Long id);
    Optional<AccommodationDetailsDto> save(AccommodationCreateUpdateDto accommodationDto);
    Optional<AccommodationDetailsDto> update(Long id, AccommodationCreateUpdateDto accommodationDto);
    void deleteById(Long id);
    Optional<AccommodationDetailsDto> markAsRented(Long id);
    Page<AccommodationShortDto> findAllPaged(AccommodationCategory category, Long hostId, String hostCountry, Integer minRooms, Boolean availableRooms,int page, int size, String sortBy, String direction);
}