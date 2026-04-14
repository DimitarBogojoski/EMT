package com.example.emt_lab_grupa_b.servise.Imp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MaterializedViewRefreshService {
    private final JdbcTemplate jdbcTemplate;

    public MaterializedViewRefreshService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedDelayString = "${app.materialized-view.refresh-ms}")
    public void refreshAccommodationStats() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW accommodation_stats_mv");
    }
}
