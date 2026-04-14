package com.example.emt_lab_grupa_b.model.dto;

import com.example.emt_lab_grupa_b.enums.AccommodationCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccommodationCreateUpdateDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Category is required")
    private AccommodationCategory category;

    @NotNull(message = "Host id is required")
    private Long hostId;

    @NotNull(message = "No. of rooms is requierd")
    @Min(value = 0,message = "No. of rooms can not be negative")
    private Integer numRooms;
}
