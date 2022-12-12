package com.tripworld.amenties.hotel;

import com.tripworld.amenties.Amenity;
import com.tripworld.hotels.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


public interface HotelAmenityRepository extends JpaRepository<HotelAmenity, Long> {




}
