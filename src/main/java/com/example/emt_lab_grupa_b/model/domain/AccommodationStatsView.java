package com.example.emt_lab_grupa_b.model.domain;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "accommodation_stats_mv")
@Getter
@Setter
@NoArgsConstructor
public class AccommodationStatsView {
    @Id
    @Enumerated(EnumType.STRING)
    private AccommodationCategory category;

    @Column(name = "total_accommodations")
    private Long totalAccommodations;

    @Column(name = "total_rooms")
    private Long totalRooms;

    @Column(name = "avg_rooms")
    private BigDecimal avgRooms;
}
