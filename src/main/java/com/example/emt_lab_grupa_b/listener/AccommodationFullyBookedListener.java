package com.example.emt_lab_grupa_b.listener;

import com.example.emt_lab_grupa_b.model.domain.ActivityLog;
import com.example.emt_lab_grupa_b.repository.ActivityLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccommodationFullyBookedListener {

    private final ActivityLogRepository activityLogRepository;

    public AccommodationFullyBookedListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @EventListener
    public void onAccommodationRented(AccommodationRentedEvent event) {
        if (event.remainingRooms() == 0) {
            ActivityLog log = new ActivityLog();
            log.setAccommodationId(event.accommodationId());
            log.setAccommodationName(event.accommodationName());
            log.setEventType("FULLY_BOOKED");
            log.setOccurredAt(event.occurredAt());
            activityLogRepository.save(log);
        }
    }
}
