package com.tripworld.amenties;


import com.tripworld.amenities.AmenityRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<?> getAmenitiesById(@PathVariable Long id) {
        return ResponseEntity.ok(amenityService.findById(id));
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
