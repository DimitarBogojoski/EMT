package com.example.emt_lab_grupa_b.model.dto;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDetailsDto {
    private Long id;
    private String name;
    private AccommodationCategory category;
    private Integer numRooms;
    private String hostFullName;
    private String hostCountry;
}
