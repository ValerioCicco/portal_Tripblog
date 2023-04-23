package com.tripblog.portal.persistence.repositories;

import com.tripblog.portal.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
