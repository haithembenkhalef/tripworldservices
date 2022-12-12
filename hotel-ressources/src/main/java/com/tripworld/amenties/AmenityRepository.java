package com.tripworld.amenties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {


}
