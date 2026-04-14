package com.example.emt_lab_grupa_b.repository;

import com.example.emt_lab_grupa_b.model.domain.AccommodationView;
import com.example.emt_lab_grupa_b.model.projection.AccommodationViewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationViewRepository extends JpaRepository<AccommodationView,Long> {
List<AccommodationViewProjection> findAllBy();
}
