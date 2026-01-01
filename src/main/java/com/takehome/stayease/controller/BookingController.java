package com.takehome.stayease.controller;

import com.takehome.stayease.dto.BookingRequest;
import com.takehome.stayease.entity.Booking;
import com.takehome.stayease.entity.User;
import com.takehome.stayease.service.BookingService;
import com.takehome.stayease.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    // Customer: book a room
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{hotelId}")
    public ResponseEntity<Booking> createBooking(@PathVariable Long hotelId,
                                                 @Valid @RequestBody BookingRequest request,
                                                 Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        Booking booking = bookingService.createBooking(hotelId, user.getId(), request);
        return ResponseEntity.ok(booking);
    }

    // Customer: view their booking
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBooking(bookingId));
    }

    // Manager: cancel booking
    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
