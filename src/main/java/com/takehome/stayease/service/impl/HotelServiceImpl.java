package com.takehome.stayease.service.impl;

import com.takehome.stayease.dto.HotelRequest;
import com.takehome.stayease.dto.HotelResponse;
import com.takehome.stayease.entity.Hotel;
import com.takehome.stayease.exception.ResourceNotFoundException;
import com.takehome.stayease.repository.HotelRepository;
import com.takehome.stayease.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelResponse createHotel(HotelRequest request) {

        int totalRooms = request.getTotalRooms() != null
                ? request.getTotalRooms()
                : request.getAvailableRooms();

        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .location(request.getLocation())
                .description(request.getDescription())
                .totalRooms(totalRooms)
                .availableRooms(request.getAvailableRooms())
                .build();

        return mapToResponse(hotelRepository.save(hotel));
    }

    @Override
    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public HotelResponse updateHotel(Long hotelId, HotelRequest request) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found"));

        if (request.getName() != null) {
            hotel.setName(request.getName());
        }

        if (request.getLocation() != null) {
            hotel.setLocation(request.getLocation());
        }

        if (request.getDescription() != null) {
            hotel.setDescription(request.getDescription());
        }

        if (request.getTotalRooms() != null) {
            hotel.setTotalRooms(request.getTotalRooms());
        }

        if (request.getAvailableRooms() != null) {
            hotel.setAvailableRooms(request.getAvailableRooms());
        }

        return mapToResponse(hotelRepository.save(hotel));
    }

    @Override
    public void deleteHotel(Long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found"));

        hotelRepository.delete(hotel);
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .totalRooms(hotel.getTotalRooms())
                .availableRooms(hotel.getAvailableRooms())
                .build();
    }
}