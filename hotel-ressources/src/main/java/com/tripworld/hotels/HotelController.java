package com.tripworld.hotels;

import com.tripworld.utility.Utility;
import com.tripworld.amenities.Amenity;
import com.tripworld.amenities.AmenityRegistrationRequest;
import com.tripworld.amenties.AmenityService;
import com.tripworld.amenities.hotel.HotelAmenityRegistrationRequest;
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

    @GetMapping("{id}/rooms/{roomId}")
    ResponseEntity<?> getRoomHotelById(@PathVariable Long id, @PathVariable Long roomId) {
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel.getRooms().stream()
                .filter(room -> room.getRoomId().equals(roomId))
                .collect(Utility.toSingleton()));
    }

    @GetMapping("{id}/amenities")
    ResponseEntity<?> getAmenitiesHotel(@PathVariable Long id) {
        List<?> amenities = hotelService.findAmenitiesByHotelId(id);
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("{id}/amenities/{amenityId}")
    ResponseEntity<?> geAmenityHotelById(@PathVariable Long id, @PathVariable Long amenityId) {
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel.getHotelAmenities().stream()
                .filter(hotelAmenity -> hotelAmenity.getAmenity().getAmenityId().equals(amenityId))
                .collect(Utility.toSingleton()));
    }

    @GetMapping("/search/findHotelByName/{name}")
    ResponseEntity<?> getHotelByName(@PathVariable String name) {
        return ResponseEntity.ok(hotelService.findByName(name));
    }

    @GetMapping("/search/findHotelByCityCode/{cityCode}")
    ResponseEntity<?> getHotelByCityCode(@PathVariable String cityCode) {
        return ResponseEntity.ok(hotelService.findByCityCode(cityCode));
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
