/*
package com.tripworld.hotels;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class HotelAssembler implements RepresentationModelAssembler<Hotel, EntityModel<Hotel>> {
    private RepositoryEntityLinks entityLinks;

    HotelAssembler(RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }


    @Override
    public EntityModel<Hotel> toModel(Hotel hotel) {


        return EntityModel.of(hotel, //
                linkTo(methodOn(HotelController.class).getHotel(hotel.getHotelId())).withSelfRel(),
                linkTo(methodOn(HotelController.class).getHotels()).withRel("hotels"),
                linkTo(methodOn(HotelController.class).getHotels()).withRel("hotels"));

    }
}
*/
