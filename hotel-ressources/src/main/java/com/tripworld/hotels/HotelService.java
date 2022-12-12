package com.tripworld.hotels;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public Hotel findById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        return hotel;
    }

    public Hotel registerHotel(HotelRegistrationRequest hotelRegistrationRequest) {
        Hotel hotel = Hotel.builder()
                .hotelName(hotelRegistrationRequest.hotelName())
                .cityCode(hotelRegistrationRequest.cityCode())
                .description(hotelRegistrationRequest.description())
                .build();
        log.info("Customer saved {}", hotel);
        return hotelRepository.saveAndFlush(hotel);
    }


    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel updateHotel(HotelRegistrationRequest hotelRegistrationRequest, Long id) {
        Hotel hotel = this.findById(id);
        hotel.setHotelName(hotelRegistrationRequest.hotelName());
        hotel.setDescription(hotelRegistrationRequest.description());
        hotel.setCityCode(hotelRegistrationRequest.cityCode());
        return hotelRepository.saveAndFlush(hotel);
    }

    public void deleteHotelBy(Long id) {
        hotelRepository.deleteById(id);
    }
}
