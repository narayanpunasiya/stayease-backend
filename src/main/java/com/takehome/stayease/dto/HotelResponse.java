package com.takehome.stayease.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {

    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer totalRooms;
    private Integer availableRooms;
}