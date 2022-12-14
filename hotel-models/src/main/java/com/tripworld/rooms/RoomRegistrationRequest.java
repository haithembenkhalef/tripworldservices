package com.tripworld.rooms;


import javax.validation.constraints.NotNull;

public record RoomRegistrationRequest(@NotNull String description) {
}
