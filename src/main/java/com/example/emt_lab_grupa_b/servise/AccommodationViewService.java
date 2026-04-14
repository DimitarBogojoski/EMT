package com.example.emt_lab_grupa_b.servise;

import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.projection.AccommodationViewProjection;

import java.util.List;

public interface AccommodationViewService {
    public List<AccommodationViewProjection> findAll();
}
