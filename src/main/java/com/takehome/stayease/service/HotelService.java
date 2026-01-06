package com.takehome.stayease.service;

import com.takehome.stayease.dto.HotelRequest;
import com.takehome.stayease.dto.HotelResponse;
import com.takehome.stayease.entity.Hotel;
import com.takehome.stayease.exception.ResourceNotFoundException;
import com.takehome.stayease.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setName(request.getName());
        hotel.setLocation(request.getLocation());
        hotel.setDescription(request.getDescription());
        hotel.setTotalRooms(request.getTotalRooms());
        hotel.setAvailableRooms(request.getAvailableRooms());

        hotel = hotelRepository.save(hotel);
        return mapToResponse(hotel);
    }

    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public HotelResponse updateHotel(Long hotelId, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        if (request.getName() != null) hotel.setName(request.getName());
        if (request.getDescription() != null) hotel.setDescription(request.getDescription());
        if (request.getAvailableRooms() != null) hotel.setAvailableRooms(request.getAvailableRooms());
        if (request.getTotalRooms() != null) hotel.setTotalRooms(request.getTotalRooms());
        if (request.getLocation() != null) hotel.setLocation(request.getLocation());

        hotelRepository.save(hotel);
        return mapToResponse(hotel);
    }

    public void deleteHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotelRepository.delete(hotel);
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .availableRooms(hotel.getAvailableRooms())
                .build();
    }
}
