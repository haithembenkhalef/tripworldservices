package com.tripworld.hotels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByHotelName(@Param("name") String name);

    List<?> findAmenitiesByHotelId(@Param("hotelId") Long id);

    List<Hotel> findHotelByCityCode(@Param("cityCode") String cityCode);


}
