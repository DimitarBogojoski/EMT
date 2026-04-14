package com.example.emt_lab_grupa_b.repository;

import com.example.emt_lab_grupa_b.model.domain.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {
}
