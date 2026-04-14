package com.example.emt_lab_grupa_b.model.projection;

public interface AccommodationViewProjection {
    Long getId();
    String getName();
    String getCategory();
    Integer getNumRooms();
    String getHostFullName();
    String getCountryName();
}
