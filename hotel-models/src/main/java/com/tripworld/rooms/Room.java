package com.tripworld.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.rating.Rating;
import com.tripworld.amenities.room.RoomAmenity;
import com.tripworld.hotels.Hotel;
import lombok.*;
import org.hibernate.annotations.Formula;

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
    @ToString.Exclude
    List<RoomAmenity> amenities;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    List<Rating> rating;

    @Formula(value = "(select avg(ra.value) from ratings ra where ra.room_id = room_id)")
    private Double ratingValue;

}
