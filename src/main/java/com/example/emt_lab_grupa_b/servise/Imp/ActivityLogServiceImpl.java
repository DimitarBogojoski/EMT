package com.example.emt_lab_grupa_b.servise.Imp;

import com.example.emt_lab_grupa_b.model.domain.ActivityLog;
import com.example.emt_lab_grupa_b.repository.ActivityLogRepository;
import com.example.emt_lab_grupa_b.servise.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public Page<ActivityLog> findAll(int page, int size) {
        return activityLogRepository.findAll(
                PageRequest.of(page, size, Sort.by("occurredAt").descending())
        );
    }
}
