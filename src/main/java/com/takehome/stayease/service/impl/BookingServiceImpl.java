package com.takehome.stayease.service.impl;

import lombok.RequiredArgsConstructor;
import com.takehome.stayease.dto.BookingRequest;
import com.takehome.stayease.dto.BookingResponse;
import com.takehome.stayease.entity.Booking;
import com.takehome.stayease.entity.Hotel;
import com.takehome.stayease.entity.User;
import com.takehome.stayease.exception.NoRoomsAvailableException;
import com.takehome.stayease.exception.ResourceNotFoundException;
import com.takehome.stayease.repository.BookingRepository;
import com.takehome.stayease.repository.HotelRepository;
import com.takehome.stayease.repository.UserRepository;
import com.takehome.stayease.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public BookingResponse createBooking(Long hotelId, BookingRequest request) {

        User user = getLoggedInUser();

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        if (hotel.getAvailableRooms() <= 0) {
            throw new NoRoomsAvailableException("No rooms available");
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);

        Booking booking = Booking.builder()
                .hotel(hotel)
                .user(user)
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .build();

        Booking saved = bookingRepository.save(booking);
        hotelRepository.save(hotel);

        return mapToResponse(saved);
    }

    @Override
    public BookingResponse getBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        validateOwnership(booking);

        return mapToResponse(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // validateOwnership(booking);

        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);

        hotelRepository.save(hotel);
        bookingRepository.delete(booking);
    }

    // ---------------- helpers ----------------

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private void validateOwnership(Booking booking) {
        User user = getLoggedInUser();

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }
    }

    private BookingResponse mapToResponse(Booking booking) {

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .hotelId(booking.getHotel().getId())
                .userId(booking.getUser().getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .build();
    }
}