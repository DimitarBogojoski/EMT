package com.example.emt_lab_grupa_b.model.projection;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;

public interface AccommodationShortProjection {
    Long getId();
    String getName();
    AccommodationCategory getCategory();
    Integer getNumRooms();
}
