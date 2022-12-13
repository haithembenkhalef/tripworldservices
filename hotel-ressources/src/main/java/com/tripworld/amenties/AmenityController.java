package com.tripworld.amenties;


import com.tripworld.hotels.Hotel;
import com.tripworld.hotels.HotelRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/amenities")
public class AmenityController {

    private final AmenityService amenityService;

    @GetMapping
    public ResponseEntity<?> getAmenities() {
        return ResponseEntity.ok(amenityService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> registerAmenity(@RequestBody AmenityRegistrationRequest amenityRegistrationRequest) {
      return ResponseEntity.ok(amenityService.registerAmenity(amenityRegistrationRequest));
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenityById(id);
        return ResponseEntity.noContent().build();
    }
}
