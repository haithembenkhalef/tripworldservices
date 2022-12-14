package com.tripworld.amenties;


import com.tripworld.amenities.Amenity;
import com.tripworld.amenities.AmenityRegistrationRequest;
import com.tripworld.amenities.AmenityRepository;
import com.tripworld.exceptions.NoRecordFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public Amenity findById(Long id) {
        Amenity amenity = amenityRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("Amenity", id));
        return amenity;
    }

    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    public Amenity registerAmenity(AmenityRegistrationRequest amenityRegistrationRequest) {
        Amenity amenity = Amenity.builder()
                .description(amenityRegistrationRequest.description())
                .shortDesc(amenityRegistrationRequest.shortDescription())
                .build();
        log.info("Amenity saved {}", amenity);
        return amenityRepository.saveAndFlush(amenity);
    }


    public void deleteAmenityById(Long id) {
        if(amenityRepository.existsById(id)) amenityRepository.deleteById(id);
        else throw new NoRecordFoundException("Amenity", id);
    }
}
