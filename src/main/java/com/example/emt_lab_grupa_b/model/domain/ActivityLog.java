package com.example.emt_lab_grupa_b.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_log")
@Getter
@Setter
@NoArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_id", nullable = false)
    private Long accommodationId;

    @Column(name = "accommodation_name", nullable = false)
    private String accommodationName;

    @Column(name = "event_time", nullable = false)
    private LocalDateTime occurredAt;

    @Column(name = "event_type", nullable = false)
    private String eventType;
}
