package com.tripblog.portal.services;

import com.tripblog.portal.persistence.entities.Trip;
import com.tripblog.portal.persistence.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Trip getById(long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);

        if (optionalTrip.isEmpty()) {
            throw new IllegalStateException("Trip not found");
        }

        return optionalTrip.get();
    }

    public Trip create(Trip newTrip) {

        if (newTrip.getUser() == null) {
            throw new IllegalStateException("User must not be null");
        }

        newTrip = tripRepository.save(newTrip);

        return newTrip;
    }

    public Trip update(long id, Trip updateTrip) {
        if (updateTrip.getUser() == null) {
            throw new IllegalStateException("User must not be null");
        }

        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (optionalTrip.isEmpty()) {
            throw new IllegalStateException("Trip not found");
        }

        Trip tripToUpdate = optionalTrip.get();

        updateTrip.setId(tripToUpdate.getId());

        updateTrip = tripRepository.save(updateTrip);

        return updateTrip;
    }

    public Trip delete(long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);

        if (optionalTrip.isEmpty()) {
            throw new IllegalStateException("Trip not found");
        }

        Trip tripToDelete = optionalTrip.get();

        tripRepository.delete(tripToDelete);

        return tripToDelete;

    }


}
