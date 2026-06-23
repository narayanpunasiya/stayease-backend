package com.takehome.stayease.controller;

import com.takehome.stayease.dto.HotelRequest;
import com.takehome.stayease.dto.HotelResponse;
import com.takehome.stayease.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(
            @Valid @RequestBody HotelRequest request) {

        return ResponseEntity.ok(
                hotelService.createHotel(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        return ResponseEntity.ok(
                hotelService.getAllHotels()
        );
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> updateHotel(
            @PathVariable Long hotelId,
            @RequestBody HotelRequest request) {

        return ResponseEntity.ok(
                hotelService.updateHotel(hotelId, request)
        );
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(
            @PathVariable Long hotelId) {

        hotelService.deleteHotel(hotelId);

        return ResponseEntity.noContent().build();
    }
}