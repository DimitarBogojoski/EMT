package com.example.emt_lab_grupa_b.model.dto;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccommodationDto {
    @NotBlank
    private String name;

    @NotNull
    private AccommodationCategory category;

    @NotNull
    private Long hostId;

    @NotNull
    @Min(1)
    private Integer numRooms;
}
