package com.takehome.stayease.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    private String description;

    @Min(1)
    private int totalRooms;

    @Min(0)
    private int availableRooms;
}

