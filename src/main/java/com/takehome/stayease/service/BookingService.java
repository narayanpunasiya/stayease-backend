package com.takehome.stayease.service;

import lombok.RequiredArgsConstructor;
import com.takehome.stayease.dto.BookingRequest;
import com.takehome.stayease.entity.Booking;
import com.takehome.stayease.entity.Hotel;
import com.takehome.stayease.entity.User;
import com.takehome.stayease.exception.ResourceNotFoundException;
import com.takehome.stayease.repository.BookingRepository;
import com.takehome.stayease.repository.HotelRepository;
import com.takehome.stayease.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    public Booking createBooking(Long hotelId, Long userId, BookingRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        if (hotel.getAvailableRooms() <= 0) {
            throw new ResourceNotFoundException("No rooms available");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Booking booking = new Booking();
        booking.setHotel(hotel);
        booking.setUser(user);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.save(hotel);

        return bookingRepository.save(booking);
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        hotelRepository.save(hotel);
        bookingRepository.delete(booking);
    }
}
