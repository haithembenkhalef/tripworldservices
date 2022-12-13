package com.tripworld.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.amenities.room.RoomAmenity;
import com.tripworld.hotels.Hotel;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
@NamedQuery(name = "Room.findAmenitiesByRoomId", query = "select ra from RoomAmenity ra inner join  Amenity a on ra.amenity.amenityId = a.amenityId where ra.room.roomId = :roomId")
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
    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    @NotNull
    private Hotel hotel;

    @OneToMany(mappedBy = "amenity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<RoomAmenity> amenities;


//    public String getHotelName() {
//        return this.hotel.getHotelName();
//    }
}
