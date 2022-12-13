package com.tripworld.amenties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.amenties.hotel.HotelAmenity;
import com.tripworld.amenties.room.RoomAmenity;
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
@Table(name = "amenity_mst")
public class Amenity {

    @Id
    @SequenceGenerator(
            name = "amenity_sequence_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "amenity_sequence_id"
    )
    private Long amenityId;

    @NotNull
    @NotBlank
    private String description;

    @Column(name = "short_desc")
    @NotNull
    @NotBlank
    private String shortDesc;

    @OneToMany(mappedBy = "amenity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<HotelAmenity> hotelAmenities;

    @OneToMany(mappedBy = "amenity", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<RoomAmenity> roomAmenities;


}
