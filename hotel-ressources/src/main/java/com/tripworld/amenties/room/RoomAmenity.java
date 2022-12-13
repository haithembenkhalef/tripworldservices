package com.tripworld.amenties.room;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.amenties.Amenity;
import com.tripworld.rooms.Room;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @JsonIgnore
    private Room room;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    @NotNull
    private Amenity amenity;

    private boolean chargeable;

    @DecimalMin(value = "0.00")
    @Digits(integer=5, fraction=2)
    private BigDecimal amount;

}
