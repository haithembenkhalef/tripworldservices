package com.tripworld.amenties.hotel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.amenties.Amenity;
import com.tripworld.hotels.Hotel;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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

    @Id
    @SequenceGenerator(name = "hotel_amenity_sequence_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_amenity_sequence_id")
    private Long hotelAmenityId;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @NotNull
    @JsonIgnore
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    @NotNull
    private Amenity amenity;


    private boolean chargeable;

    @DecimalMin(value = "0.00")
    @Digits(integer=5, fraction=2)
    private BigDecimal amount;


}
