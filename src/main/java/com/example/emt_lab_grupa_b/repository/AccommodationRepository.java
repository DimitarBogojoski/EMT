package com.example.emt_lab_grupa_b.repository;

import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.model.projection.AccommodationExtendedProjection;
import com.example.emt_lab_grupa_b.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {
    Page<AccommodationShortProjection> findAllBy(Pageable pageable);

    Optional<AccommodationExtendedProjection> findProjectedById(Long id);

    @EntityGraph(attributePaths = {"host", "host.country"})
    Optional<Accommodation> findWithHostAndCountryById(Long id);
}
