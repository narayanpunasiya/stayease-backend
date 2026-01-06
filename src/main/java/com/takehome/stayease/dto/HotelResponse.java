package com.takehome.stayease.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HotelResponse {
    private Long id;
    private String name;
    private String location;
    private String description;
    private int availableRooms;
}
