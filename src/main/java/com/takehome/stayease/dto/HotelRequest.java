package com.takehome.stayease.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {

    private String name;

    private String location;

    private String description;

    @Min(1)
    private Integer totalRooms;

    @Min(0)
    private Integer availableRooms;
}