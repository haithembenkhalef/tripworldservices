package com.tripworld.amenties.hotel;


import com.tripworld.amenities.hotel.HotelAmenity;
import com.tripworld.amenities.hotel.HotelAmenityRegistrationRequest;
import com.tripworld.amenities.hotel.HotelAmenityRepository;
import com.tripworld.amenties.AmenityService;
import com.tripworld.exceptions.NoRecordFoundException;
import com.tripworld.hotels.HotelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class HotelAmenityService {

    private final HotelAmenityRepository hotelAmenityRepository;
    private final HotelService hotelService;

    private final AmenityService amenityService;


    public HotelAmenity registerLink(HotelAmenityRegistrationRequest hotelAmenityRegistrationRequest) {
        HotelAmenity hotelAmenity = HotelAmenity.builder()
                .hotel(hotelService.findById(hotelAmenityRegistrationRequest.hotelId()))
                .amenity(amenityService.findById(hotelAmenityRegistrationRequest.amenityId()))
                .chargeable(hotelAmenityRegistrationRequest.chargeable())
                .amount(hotelAmenityRegistrationRequest.amount())
                .build();
        hotelAmenityRepository.saveAndFlush(hotelAmenity);
        return hotelAmenity;
    }

    public void deleteHotelAmenityById(Long id) {
        if(hotelAmenityRepository.existsById(id)) hotelAmenityRepository.deleteById(id);
        else throw new NoRecordFoundException("HotelAmenity", id);
    }
}
