package com.tripworld.rating;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripworld.rooms.Room;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
@NamedQuery(name = "Rating.getRoomRating", query = "select ra.room, avg(ra.value) from Rating ra where ra.room.roomId = :roomId")
public class Rating {

    @Id
    @SequenceGenerator(
            name = "rating_sequence_id"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_sequence_id"
    )
    private Long ratingId;

    @NotNull
    @Min(1)
    @Max(5)
    private int value = 1;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    Room room;
}
