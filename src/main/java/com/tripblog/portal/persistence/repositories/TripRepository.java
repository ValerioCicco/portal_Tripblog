package com.tripblog.portal.persistence.repositories;

import com.tripblog.portal.persistence.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
