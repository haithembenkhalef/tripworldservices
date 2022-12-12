package com.tripworld.hotels;

public record HotelRegistrationRequest(String hotelName,
                                       String description,
                                       String cityCode) {
}
