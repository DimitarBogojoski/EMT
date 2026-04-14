package com.example.emt_lab_grupa_b.model.dto;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;

public record AccommodationShortDto(
    Long id,
    String name,
    AccommodationCategory category,
    Integer numRooms
)
{}