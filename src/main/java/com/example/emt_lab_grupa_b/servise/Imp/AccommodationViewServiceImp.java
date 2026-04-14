package com.example.emt_lab_grupa_b.servise.Imp;

import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.projection.AccommodationViewProjection;
import com.example.emt_lab_grupa_b.repository.AccommodationViewRepository;
import com.example.emt_lab_grupa_b.servise.AccommodationViewService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccommodationViewServiceImp implements AccommodationViewService {
    private final AccommodationViewRepository accommodationViewRepository;

    public AccommodationViewServiceImp(AccommodationViewRepository accommodationViewRepository) {
        this.accommodationViewRepository = accommodationViewRepository;
    }

    @Override
    public List<AccommodationViewProjection> findAll() {
        return accommodationViewRepository.findAllBy();
    }
}
