package com.tripworld.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.amenties.room.RoomAmenity;
import com.tripworld.hotels.Hotel;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence_id"
    )
    private Long roomId;

    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    @NotNull
    @JsonIgnore
    private Hotel hotel;

    @OneToMany(mappedBy = "amenity")
    @JsonIgnore
    List<RoomAmenity> amenities;


//    public String getHotelName() {
//        return this.hotel.getHotelName();
//    }
}
