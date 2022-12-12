package com.tripworld.hotels;



import com.tripworld.amenties.hotel.HotelAmenity;
import com.tripworld.exceptions.validators.TargetNotFound;
import com.tripworld.rooms.Room;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;


import javax.persistence.*;
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
@NamedQuery(name = "Hotel.findAmenitiesByHotelId", query = "select a from Amenity a inner join HotelAmenity ha on ha.amenity.amenityId = a.amenityId where ha.hotel.hotelId = :hotelId")
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
    private String hotelName;

    @NotNull
    private String description;

    @NotNull
    @Size(max = 23)
    private String cityCode;


    @OneToMany(mappedBy = "hotel")
    Set<Room> rooms;

    @OneToMany(mappedBy = "hotel")
    List<HotelAmenity> hotelAmenities;


}
