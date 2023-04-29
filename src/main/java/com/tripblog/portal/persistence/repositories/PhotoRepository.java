package com.tripblog.portal.persistence.repositories;

import com.tripblog.portal.persistence.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
