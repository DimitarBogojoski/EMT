package com.example.emt_lab_grupa_b.repository;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.domain.AccommodationStatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatsRepository extends JpaRepository<AccommodationStatsView, AccommodationCategory> {
}
