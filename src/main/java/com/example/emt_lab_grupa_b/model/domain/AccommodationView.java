package com.example.emt_lab_grupa_b.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "accommodation_view")
@Getter
@Setter
@NoArgsConstructor
public class AccommodationView {
    @Id
    private Long id;

    private String name;
    private String category;

    @Column(name = "num_rooms")
    private Integer numRooms;

    @Column(name = "host_full_name")
    private String hostFullName;

    @Column(name = "country_name")
    private String countryName;
}
