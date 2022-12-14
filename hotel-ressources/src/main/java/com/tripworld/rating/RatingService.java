package com.tripworld.rating;


import com.tripworld.rooms.Room;
import com.tripworld.rooms.RoomRegistrationRequest;
import com.tripworld.rooms.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    private final RoomService roomService;

    public Rating rateRoom(Long id, RatingRequest ratingRequest) {
        Rating rating = Rating.builder()
                        .room(roomService.findById(id))
                        .value(ratingRequest.value())
                        .build();
        log.info("Rating Saved {}", rating);
        return ratingRepository.saveAndFlush(rating);
    }
}
