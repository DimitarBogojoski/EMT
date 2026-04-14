package com.example.emt_lab_grupa_b.servise.filter;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationFilter {
    private AccommodationCategory category;
    private Long hostId;
    private String hostCountry;
    private Integer minRooms;
    private Boolean availableRooms;
}
