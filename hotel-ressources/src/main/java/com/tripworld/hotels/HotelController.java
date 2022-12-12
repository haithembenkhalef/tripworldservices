/*
package com.tripworld.hotels;

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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class HotelController {

    private final HotelService hotelService;

    private final HotelAssembler hotelAssembler;

    @GetMapping("/api/v1/hotels")
    public ResponseEntity<?> getHotels() {
        List<Hotel> hotels = hotelService.findAll();
        if(hotels.isEmpty()) {
            EmbeddedWrapper wrapper = new EmbeddedWrappers(false).emptyCollectionOf(Hotel.class);
            return ResponseEntity.ok(CollectionModel.of(Arrays.asList(wrapper),
                    linkTo(methodOn(HotelController.class).getHotels()).withSelfRel()));
        }
        List<EntityModel<Hotel>> hotelsEntity = hotelService.findAll().stream()
                .map(hotel -> hotelAssembler.toModel(hotel))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(hotelsEntity,
                linkTo(methodOn(HotelController.class).getHotels())
                        .withSelfRel()));
    }

    @GetMapping("/api/v1/hotels/{id}")
    ResponseEntity<EntityModel<Hotel>> getHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotelAssembler.toModel(hotel));
    }

    @PostMapping("/api/v1/hotels")
    public ResponseEntity<?> registerHotel(@RequestBody HotelRegistrationRequest hotelRegistrationRequest) {
        EntityModel<Hotel> entityModel = hotelAssembler.toModel(hotelService.registerHotel(hotelRegistrationRequest));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/api/v1/hotels/{id}")
    ResponseEntity<?> updateHotel(@RequestBody HotelRegistrationRequest hotelRegistrationRequest, @PathVariable Long id) {
        EntityModel<Hotel> entityModel = hotelAssembler.toModel(hotelService.updateHotel(hotelRegistrationRequest, id));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/api/v1/hotels/{id}")
    ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotelBy(id);
        return ResponseEntity.noContent().build();
    }



}
*/
