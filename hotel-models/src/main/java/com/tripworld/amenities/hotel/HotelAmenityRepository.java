package com.tripworld.amenities.hotel;

import com.tripworld.amenities.hotel.HotelAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Long> {

    List<HotelAmenity> findAllByHotel_HotelName(@Param("name") String name);

    boolean existsByHotel_HotelId(@Param("hotelId") Long hotelId);

    boolean existsByAmenity_AmenityId(@Param("amenityId") Long amenityId);

    void deleteByHotel_HotelId(@Param("hotelId") Long hotelId);

    void deleteByAmenity_AmenityId(@Param("amenityId") Long amenityId);


}
