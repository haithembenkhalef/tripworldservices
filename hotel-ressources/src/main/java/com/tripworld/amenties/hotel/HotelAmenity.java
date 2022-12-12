package com.tripworld.amenties.hotel;


import com.tripworld.amenties.Amenity;
import com.tripworld.hotels.Hotel;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel_amenities")
public class HotelAmenity {

    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    @Id
    @SequenceGenerator(name = "hotel_amenity_sequence_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_amenity_sequence_id")
    private Long hotelAmenityId;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    private boolean chargeable;

    private BigDecimal amount;


}
