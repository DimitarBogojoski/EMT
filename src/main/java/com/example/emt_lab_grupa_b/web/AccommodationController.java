package com.example.emt_lab_grupa_b.web;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import com.example.emt_lab_grupa_b.model.domain.AccommodationStatsView;
import com.example.emt_lab_grupa_b.model.domain.ActivityLog;
import com.example.emt_lab_grupa_b.model.dto.AccommodationCreateUpdateDto;
import com.example.emt_lab_grupa_b.model.dto.AccommodationDetailsDto;
import com.example.emt_lab_grupa_b.model.dto.AccommodationShortDto;
import com.example.emt_lab_grupa_b.model.projection.AccommodationExtendedProjection;
import com.example.emt_lab_grupa_b.model.projection.AccommodationViewProjection;
import com.example.emt_lab_grupa_b.repository.AccommodationRepository;
import com.example.emt_lab_grupa_b.repository.AccommodationStatsRepository;
import com.example.emt_lab_grupa_b.servise.AccommodationService;
import com.example.emt_lab_grupa_b.servise.AccommodationViewService;
import com.example.emt_lab_grupa_b.servise.ActivityLogService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final ActivityLogService activityLogService;
    private final AccommodationStatsRepository accommodationStatsRepository;
    private final AccommodationViewService accommodationViewService;
    private final AccommodationRepository accommodationRepository;

    public AccommodationController(AccommodationService accommodationService, ActivityLogService activityLogService, AccommodationStatsRepository accommodationStatsRepository, AccommodationViewService accommodationViewService, AccommodationRepository accommodationRepository) {
        this.accommodationService = accommodationService;
        this.activityLogService = activityLogService;
        this.accommodationStatsRepository = accommodationStatsRepository;
        this.accommodationViewService = accommodationViewService;
        this.accommodationRepository = accommodationRepository;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDetailsDto> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<AccommodationDetailsDto> save(@Valid @RequestBody AccommodationCreateUpdateDto accommodationDto) {
        return accommodationService.save(accommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AccommodationDetailsDto> update(@PathVariable Long id,
                                                @Valid @RequestBody AccommodationCreateUpdateDto accommodationDto) {
        return accommodationService.update(id, accommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accommodationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<AccommodationDetailsDto> markAsRented(@PathVariable Long id) {
        return accommodationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<AccommodationShortDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) AccommodationCategory category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) String hostCountry,
            @RequestParam(required = false) Integer minRooms,
            @RequestParam(required = false) Boolean availableRooms
    ){
        return ResponseEntity.ok(
                accommodationService.findAllPaged(category, hostId, hostCountry, minRooms, availableRooms, page, size, sortBy, direction)
        );
    }

    @GetMapping("/activities")
    public ResponseEntity<Page<ActivityLog>> getActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(activityLogService.findAll(page, size));
    }

    @GetMapping("/stats")
    public List<AccommodationStatsView> stats() {
        return accommodationStatsRepository.findAll();
    }

    @GetMapping("/view")
    public ResponseEntity<List<AccommodationViewProjection>> getViewData() {
        return ResponseEntity.ok(accommodationViewService.findAll());
    }

    @GetMapping("/projection/{id}")
    public ResponseEntity<AccommodationExtendedProjection> findProjectedById(@PathVariable Long id) {
        return accommodationRepository.findProjectedById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}