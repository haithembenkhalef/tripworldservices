package com.tripworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomPojo{

    @NotNull
    Long hotelId;

    @NotNull
    String description;
}
