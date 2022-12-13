package com.tripworld.processors;

import com.tripworld.hotels.HotelRepository;
import com.tripworld.pojo.RoomPojo;
import com.tripworld.rooms.Room;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@AllArgsConstructor
public class RoomItemProcessor implements ItemProcessor<RoomPojo, Room> {

    private final HotelRepository hotelRepository;

    @Override
    public Room process(RoomPojo roomPojo) throws Exception {
        return Room.builder()
                .hotel(hotelRepository.getById(roomPojo.getHotelId()))
                .description(roomPojo.getDescription())
                .build();
    }
}
