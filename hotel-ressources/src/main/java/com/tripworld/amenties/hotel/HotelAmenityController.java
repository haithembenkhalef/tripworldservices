package com.tripworld.amenties.hotel;


import com.tripworld.hotels.Hotel;
import com.tripworld.hotels.HotelRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotelamenities")
public class HotelAmenityController {

    private final HotelAmenityService hotelAmenityService;

    @PostMapping
    public ResponseEntity<?> registerLink(@RequestBody HotelAmenityRegistrationRequest request) {
        HotelAmenity hotelAmenity = hotelAmenityService.registerLink(request);
        return ResponseEntity
                .ok(hotelAmenity);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        hotelAmenityService.deleteHotelAmenityById(id);
        return ResponseEntity.noContent().build();
    }


}
