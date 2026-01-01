package com.takehome.stayease.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {

    @NotNull
    @Future(message = "Check-in date must be in the future")
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;
}
