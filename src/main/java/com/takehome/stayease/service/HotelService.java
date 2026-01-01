package com.takehome.stayease.service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.takehome.stayease.dto.HotelRequest;
import com.takehome.stayease.entity.Hotel;
import com.takehome.stayease.exception.ResourceNotFoundException;
import com.takehome.stayease.repository.HotelRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public Hotel createHotel(HotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setName(request.getName());
        hotel.setLocation(request.getLocation());
        hotel.setDescription(request.getDescription());
        hotel.setTotalRooms(request.getTotalRooms());
        hotel.setAvailableRooms(request.getAvailableRooms());
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotel.setName(request.getName());
        hotel.setAvailableRooms(request.getAvailableRooms());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotelRepository.delete(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
