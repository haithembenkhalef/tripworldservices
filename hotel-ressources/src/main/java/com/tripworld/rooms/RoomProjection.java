package com.tripworld.rooms;

import com.tripworld.hotels.Hotel;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "simplified", types = { Room.class })
public interface RoomProjection {

    String getDescription();
    String getHotelName();

}
