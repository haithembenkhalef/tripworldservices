package com.tripworld.hotels;


import com.tripworld.exceptions.NoRecordFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public Hotel findById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("Hotel", id));
        return hotel;
    }

    public List<Hotel> findAll(Optional<Integer> page, Optional<Integer> size) {
        List<Hotel> hotels = null;
        if(!page.isPresent() && size.isPresent())
            hotels =  hotelRepository.findAll(PageRequest.ofSize(size.get())).getContent();
        else
        if(size.isPresent())
            hotels =  hotelRepository.findAll(PageRequest.of(page.get(), size.get())).getContent();
        else hotels = hotelRepository.findAll();

        return hotels ;
    }

    public List<?> findAmenitiesByHotelId(Long id) {
        return hotelRepository.findAmenitiesByHotelId(id);
    }

    public Hotel registerHotel(HotelRegistrationRequest hotelRegistrationRequest) {
        Hotel hotel = Hotel.builder()
                .hotelName(hotelRegistrationRequest.hotelName())
                .cityCode(hotelRegistrationRequest.cityCode())
                .description(hotelRegistrationRequest.description())
                .build();
        log.info("Hotel saved {}", hotel);
        return hotelRepository.saveAndFlush(hotel);
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


    public List<Hotel> findByName(String name){
        return this.hotelRepository.findByHotelName(name);
    }

    public List<Hotel> findByCityCode(String cityCode){
        return this.hotelRepository.findHotelByCityCode(cityCode);
    }


}
