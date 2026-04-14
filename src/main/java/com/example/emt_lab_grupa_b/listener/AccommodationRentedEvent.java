package com.example.emt_lab_grupa_b.listener;

import java.time.LocalDateTime;

public record AccommodationRentedEvent(
        Long accommodationId,
        String accommodationName,
        Integer remainingRooms,
        LocalDateTime occurredAt
) {
}
