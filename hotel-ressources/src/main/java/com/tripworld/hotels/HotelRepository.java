package com.tripworld.hotels;

import com.tripworld.amenties.Amenity;
import com.tripworld.amenties.hotel.HotelAmenity;
import com.tripworld.rooms.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import javax.persistence.NamedQuery;
import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByHotelName(@Param("name") String name);

    List<Amenity> findAmenitiesByHotelId(@Param("hotelId") Long id);

    List<Room> findAllByHotelId(@Param("hotelId") Long id);
}
