package com.tripworld.hotels;

import javax.validation.constraints.NotNull;

public record HotelRegistrationRequest(@NotNull String hotelName,
                                       @NotNull String description,
                                       @NotNull String cityCode) {
}
