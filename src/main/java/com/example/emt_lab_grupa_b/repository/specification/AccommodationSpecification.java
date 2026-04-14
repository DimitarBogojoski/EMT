package com.example.emt_lab_grupa_b.repository.specification;

import com.example.emt_lab_grupa_b.model.domain.Accommodation;
import com.example.emt_lab_grupa_b.servise.filter.AccommodationFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class AccommodationSpecification {
    public static Specification<Accommodation> filter(AccommodationFilter filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getCategory() != null) {
                predicates.add(cb.equal(root.get("category"), filter.getCategory()));
            }

            if (filter.getHostId() != null) {
                predicates.add(cb.equal(root.get("host").get("id"), filter.getHostId()));
            }

            if (filter.getHostCountry() != null && !filter.getHostCountry().isBlank()) {
                predicates.add(cb.equal(
                        cb.lower(root.get("host").get("country").get("name")),
                        filter.getHostCountry().toLowerCase()
                ));
            }

            if (filter.getMinRooms() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("numRooms"), filter.getMinRooms()));
            }

            if (filter.getAvailableRooms() != null) {
                if (filter.getAvailableRooms()) {
                    predicates.add(cb.greaterThan(root.get("numRooms"), 0));
                } else {
                    predicates.add(cb.equal(root.get("numRooms"), 0));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
