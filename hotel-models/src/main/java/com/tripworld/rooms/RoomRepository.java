package com.tripworld.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<?> findAmenitiesByRoomId(@Param("roomId") Long id);
}
