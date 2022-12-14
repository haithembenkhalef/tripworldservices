package com.tripworld.amenties.hotel;


import com.tripworld.amenities.hotel.HotelAmenity;
import com.tripworld.amenities.hotel.HotelAmenityRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotelAmenities")
public class HotelAmenityController {

    private final HotelAmenityService hotelAmenityService;

    @PostMapping
    public ResponseEntity<?> registerLink(@RequestBody HotelAmenityRegistrationRequest request) {
        HotelAmenity hotelAmenity = hotelAmenityService.registerLink(request);
        return ResponseEntity
                .ok(hotelAmenity);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteLink(@PathVariable Long id) {
        hotelAmenityService.deleteHotelAmenityById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/hotel")
    ResponseEntity<?> deleteLinkByHotelId(@PathVariable Long id) {
        hotelAmenityService.deleteHotelAmenityByHotelId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/amenity")
    ResponseEntity<?> deleteLinkByAmenityId(@PathVariable Long id) {
        hotelAmenityService.deleteHotelAmenityByRoomId(id);
        return ResponseEntity.noContent().build();
    }


}
