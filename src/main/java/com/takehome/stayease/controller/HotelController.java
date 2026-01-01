package com.takehome.stayease.controller;

import com.takehome.stayease.dto.HotelRequest;
import com.takehome.stayease.entity.Hotel;
import com.takehome.stayease.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    // Public: anyone can view hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.createHotel(request));
    }

    // Manager only
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.updateHotel(id, request));
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
