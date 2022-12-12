package com.tripworld.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


public interface RoomRepository extends JpaRepository<Room, Long> {
}
