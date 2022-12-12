package com.tripworld.amenties.room;


import com.tripworld.amenties.Amenity;
import com.tripworld.rooms.Room;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_amenities")
public class RoomAmenity {

    @Id
    @SequenceGenerator(
            name = "room_amenities_sequence_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_amenities_sequence_id"
    )
    private Long roomAmenityId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    private boolean chargeable;

    private BigDecimal amount;

}
