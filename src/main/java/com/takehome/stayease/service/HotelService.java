package com.takehome.stayease.service;

import com.takehome.stayease.dto.HotelRequest;
import com.takehome.stayease.dto.HotelResponse;

import java.util.List;

public interface HotelService {

    HotelResponse createHotel(HotelRequest request);

    List<HotelResponse> getAllHotels();

    HotelResponse updateHotel(Long hotelId, HotelRequest request);

    void deleteHotel(Long hotelId);
}