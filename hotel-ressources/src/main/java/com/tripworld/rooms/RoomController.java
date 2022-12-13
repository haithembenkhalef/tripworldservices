package com.tripworld.rooms;


import com.tripworld.utility.Utility;
import com.tripworld.amenities.Amenity;
import com.tripworld.amenities.AmenityRegistrationRequest;
import com.tripworld.amenties.AmenityService;
import com.tripworld.amenities.room.RoomAmenityRegistrationRequest;
import com.tripworld.amenties.room.RoomAmenityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    private final AmenityService amenityService;

    private final RoomAmenityService roomAmenityService;


    @GetMapping
    public ResponseEntity<?> getRooms() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getRoom(@PathVariable Long id) {
        Room room = roomService.findById(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("{id}/amenities")
    ResponseEntity<?> getAmenitiesRoom(@PathVariable Long id) {
        List<?> amenities = roomService.findAmenitiesByRoomId(id);
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("{id}/amenities/{amenityId}")
    ResponseEntity<?> geAmenityRoomById(@PathVariable Long id, @PathVariable Long amenityId) {
        Room room = roomService.findById(id);
        return ResponseEntity.ok(room.getAmenities().stream()
                .filter(roomAmenity -> roomAmenity.getAmenity().getAmenityId().equals(amenityId))
                .collect(Utility.toSingleton()));
    }

    @PostMapping("{id}/amenities")
    public ResponseEntity<?> registerAmenity(@PathVariable Long id, @RequestBody AmenityRegistrationRequest amenityRegistrationRequest) {
        Amenity amenity = amenityService.registerAmenity(amenityRegistrationRequest);
        RoomAmenityRegistrationRequest request = new RoomAmenityRegistrationRequest(
                id, amenity.getAmenityId(), amenityRegistrationRequest.chargeable(), amenityRegistrationRequest.amount());
        roomAmenityService.registerLink(request);
        return ResponseEntity
                .ok(amenity);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomByID(id);
        return ResponseEntity.noContent().build();
    }
}
