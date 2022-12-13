package com.tripworld.rooms;

import com.tripworld.hotels.Hotel;
import org.springframework.data.rest.core.config.Projection;


/** Represents a projection of the repository
 * in case using spring data rest discovery
 * for building links manually if needed.
 * @version 1.0
 * @since 1.0
 */
@Projection(name = "simplified", types = { Room.class })
public interface RoomProjection {

    String getDescription();
    String getHotelName();

}
