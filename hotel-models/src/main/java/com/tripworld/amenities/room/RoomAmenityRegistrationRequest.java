package com.tripworld.amenities.room;


import java.math.BigDecimal;

public record RoomAmenityRegistrationRequest(Long roomId,
                                             Long amenityId,
                                             boolean chargeable,
                                             BigDecimal amount) {
}
