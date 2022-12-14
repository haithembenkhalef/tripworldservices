package com.tripworld.processors;

import com.tripworld.hotels.Hotel;
import org.springframework.batch.item.ItemProcessor;

public class HotelItemProcessor implements ItemProcessor<Hotel, Hotel> {

    @Override
    public Hotel process(Hotel hotel) throws Exception {
        return hotel;
    }
}
