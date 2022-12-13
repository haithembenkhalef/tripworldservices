package com.tripworld.rooms;


import com.tripworld.exceptions.NoRecordFoundException;
import com.tripworld.hotels.Hotel;
import com.tripworld.hotels.HotelRegistrationRequest;
import com.tripworld.hotels.HotelRepository;
import com.tripworld.hotels.HotelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
