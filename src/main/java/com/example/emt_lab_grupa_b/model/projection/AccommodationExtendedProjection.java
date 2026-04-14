package com.example.emt_lab_grupa_b.model.projection;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;

public interface AccommodationExtendedProjection {
    Long getId();
    String getName();
    AccommodationCategory getCategory();
    Integer getNumRooms();
    HostNameProjection getHost();
}
