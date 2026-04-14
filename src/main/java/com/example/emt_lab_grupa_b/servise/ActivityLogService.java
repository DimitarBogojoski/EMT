package com.example.emt_lab_grupa_b.servise;

import com.example.emt_lab_grupa_b.model.domain.ActivityLog;
import org.springframework.data.domain.Page;

public interface ActivityLogService {
    Page<ActivityLog> findAll(int page, int size);
}
