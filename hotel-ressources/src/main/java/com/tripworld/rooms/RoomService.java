package com.tripworld.rooms;


import com.tripworld.exceptions.NoRecordFoundException;
import com.tripworld.hotels.HotelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    public Room findById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("Hotel", id));
        return room;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<?> findAmenitiesByRoomId(Long id) {
        return roomRepository.findAmenitiesByRoomId(id);
    }

    public Room registerRoom(Long id, RoomRegistrationRequest roomRegistrationRequest) {
        Room room = Room.builder()
                .description(roomRegistrationRequest.description())
                .hotel(hotelService.findById(id))
                .build();
        log.info("Room saved {}", room);
        return roomRepository.saveAndFlush(room);
    }


    public void deleteRoomByID(Long id) {
        if(roomRepository.existsById(id)) roomRepository.deleteById(id);
        else throw new NoRecordFoundException("Room", id);
    }
}
