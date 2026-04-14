package com.example.emt_lab_grupa_b.listener;

import com.example.emt_lab_grupa_b.model.domain.ActivityLog;
import com.example.emt_lab_grupa_b.repository.ActivityLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccommodationEventListener {
    private final ActivityLogRepository activityLogRepository;

    public AccommodationEventListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @EventListener
    public void onAccommodationRented(AccommodationRentedEvent event) {
        ActivityLog log = new ActivityLog();
        log.setAccommodationId(event.accommodationId());
        log.setAccommodationName(event.accommodationName());
        log.setEventType("RENTED");
        log.setOccurredAt(event.occurredAt());

        activityLogRepository.save(log);
    }
}
