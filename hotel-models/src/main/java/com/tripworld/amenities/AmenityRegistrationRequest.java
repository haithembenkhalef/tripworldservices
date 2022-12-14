package com.tripworld.amenities;


import java.math.BigDecimal;

public record AmenityRegistrationRequest(String description,
                                         String shortDescription,
                                         boolean chargeable,
                                         BigDecimal amount) {
}
