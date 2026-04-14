CREATE MATERIALIZED VIEW accommodation_stats_mv AS
SELECT
    category,
    COUNT(*) AS total_accommodations,
    COALESCE(SUM(num_rooms), 0) AS total_rooms,
    COALESCE(AVG(num_rooms), 0) AS avg_rooms
FROM accommodations
GROUP BY category;