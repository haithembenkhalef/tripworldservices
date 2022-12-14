package com.tripworld.amenties.room;


import com.tripworld.amenities.room.RoomAmenity;
import com.tripworld.amenities.room.RoomAmenityRegistrationRequest;
import com.tripworld.amenities.room.RoomAmenityRepository;
import com.tripworld.amenties.AmenityService;
import com.tripworld.exceptions.NoRecordFoundException;
import com.tripworld.rooms.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RoomAmenityService {

    private final RoomAmenityRepository roomAmenityRepository;

    private final RoomService roomService;

    private final AmenityService amenityService;


    public RoomAmenity registerLink(RoomAmenityRegistrationRequest roomAmenityRegistrationRequest) {
        RoomAmenity roomAmenity = RoomAmenity.builder()
                .room(roomService.findById(roomAmenityRegistrationRequest.roomId()))
                .amenity(amenityService.findById(roomAmenityRegistrationRequest.amenityId()))
                .chargeable(roomAmenityRegistrationRequest.chargeable())
                .amount(roomAmenityRegistrationRequest.amount())
                .build();
        roomAmenityRepository.saveAndFlush(roomAmenity);
        return roomAmenity;
    }

    public void deleteRoomAmenityById(Long id) {
        if(roomAmenityRepository.existsById(id)) roomAmenityRepository.deleteById(id);
        else throw new NoRecordFoundException("HotelAmenity", id);
    }
}
