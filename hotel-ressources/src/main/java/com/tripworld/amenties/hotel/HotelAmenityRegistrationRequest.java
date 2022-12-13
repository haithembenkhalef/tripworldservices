package com.tripworld.amenties.hotel;


import java.math.BigDecimal;

public record HotelAmenityRegistrationRequest(Long hotelId,
                                              Long amenityId,
                                              boolean chargeable,
                                              BigDecimal amount) {
}
