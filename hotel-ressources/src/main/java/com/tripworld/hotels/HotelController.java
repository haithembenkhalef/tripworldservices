package com.tripworld.hotels;

import com.tripworld.amenties.Amenity;
import com.tripworld.amenties.AmenityRegistrationRequest;
import com.tripworld.amenties.AmenityService;
import com.tripworld.amenties.hotel.HotelAmenityRegistrationRequest;
import com.tripworld.amenties.hotel.HotelAmenityService;
import com.tripworld.rooms.Room;
import com.tripworld.rooms.RoomRegistrationRequest;
import com.tripworld.rooms.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;

    private final RoomService roomService;

    private final AmenityService amenityService;

    private final HotelAmenityService hotelAmenityService;

    @GetMapping
    public ResponseEntity<?> getHotels(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(hotelService.findAll(page, size));
    }

    @GetMapping("{id}")
    ResponseEntity<?> getHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("{id}/rooms")
    ResponseEntity<?> getRoomsHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel.getRooms());
    }

    @GetMapping("{id}/amenities")
    ResponseEntity<?> getAmenitiesHotel(@PathVariable Long id) {
        List<Amenity> amenities = hotelService.findAmenitiesByHotelId(id);
        return ResponseEntity.ok(amenities);
    }

    @PostMapping
    public ResponseEntity<?> registerHotel(@RequestBody HotelRegistrationRequest hotelRegistrationRequest) {
        Hotel hotel = hotelService.registerHotel(hotelRegistrationRequest);
        return ResponseEntity
                .ok(hotel);
    }

    @PostMapping("{id}/rooms")
    public ResponseEntity<?> registerRoom(@PathVariable Long id, @RequestBody RoomRegistrationRequest roomRegistrationRequest) {
        Room room = roomService.registerRoom(id, roomRegistrationRequest);
        return ResponseEntity
                .ok(room);
    }

    @PostMapping("{id}/amenities")
    public ResponseEntity<?> registerAmenity(@PathVariable Long id, @RequestBody AmenityRegistrationRequest amenityRegistrationRequest) {
        Amenity amenity = amenityService.registerAmenity(amenityRegistrationRequest);
        HotelAmenityRegistrationRequest request = new HotelAmenityRegistrationRequest(
                id, amenity.getAmenityId(), amenityRegistrationRequest.chargeable(), amenityRegistrationRequest.amount());
        hotelAmenityService.registerLink(request);
        return ResponseEntity
                .ok(amenity);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelBy(id);
        return ResponseEntity.noContent().build();
    }


}
