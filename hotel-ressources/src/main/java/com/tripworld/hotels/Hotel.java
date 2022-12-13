package com.tripworld.hotels;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.amenties.hotel.HotelAmenity;
import com.tripworld.exceptions.validators.TargetNotFound;
import com.tripworld.rooms.Room;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotels")
@NamedQuery(name = "Hotel.findAmenitiesByHotelId", query = "select ha from HotelAmenity ha inner join  Amenity a on ha.amenity.amenityId = a.amenityId where ha.hotel.hotelId = :hotelId")
public class Hotel {
    @Id
    @SequenceGenerator(
            name = "hotel_sequence_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hotel_sequence_id"
    )
    private Long hotelId;

    @NotNull
    @NotBlank
    private String hotelName;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Size(max = 5)
    private String cityCode;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<Room> rooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<HotelAmenity> hotelAmenities;


}
