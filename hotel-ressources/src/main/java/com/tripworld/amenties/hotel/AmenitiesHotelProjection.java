package com.tripworld.amenties.hotel;

import com.tripworld.amenties.Amenity;
import com.tripworld.rooms.Room;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simplified", types = { HotelAmenity.class })
public interface AmenitiesHotelProjection {

     Amenity getAmenity();

}
