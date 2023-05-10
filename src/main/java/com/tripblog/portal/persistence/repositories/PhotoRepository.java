package com.tripblog.portal.persistence.repositories;

import com.tripblog.portal.persistence.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByTrip_id(long trip_id);
}
