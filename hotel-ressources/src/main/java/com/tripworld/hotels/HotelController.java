package com.tripworld.hotels;

import com.tripworld.amenties.Amenity;
import com.tripworld.rooms.Room;
import com.tripworld.rooms.RoomRegistrationRequest;
import com.tripworld.rooms.RoomRepository;
import com.tripworld.rooms.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.hateoas.server.core.EmbeddedWrappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private final HotelService hotelService;

    private final RoomService roomService;

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
        Hotel hotel = hotelService.findById(id);
        List<Amenity> amenitieshotel = hotel.getHotelAmenities().stream().map(hotelAmenity -> hotelAmenity.getAmenity()).collect(Collectors.toList());
        return ResponseEntity.ok(amenitieshotel);
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

    @PutMapping("{id}")
    ResponseEntity<?> updateHotel(@RequestBody HotelRegistrationRequest hotelRegistrationRequest, @PathVariable Long id) {
        Hotel hotel = hotelService.updateHotel(hotelRegistrationRequest, id);
        return ResponseEntity //
                .ok(hotel);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelBy(id);
        return ResponseEntity.noContent().build();
    }


}
