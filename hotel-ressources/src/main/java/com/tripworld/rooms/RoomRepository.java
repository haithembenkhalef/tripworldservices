package com.tripworld.rooms;

import com.tripworld.amenties.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<?> findAmenitiesByRoomId(@Param("roomId") Long id);
}
