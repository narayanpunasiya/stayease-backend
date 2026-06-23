package com.takehome.stayease.service;

import com.takehome.stayease.dto.BookingRequest;
import com.takehome.stayease.dto.BookingResponse;

public interface BookingService {

    BookingResponse createBooking(
            Long hotelId,
            BookingRequest request
    );

    BookingResponse getBooking(Long bookingId);

    void cancelBooking(Long bookingId);
}