package com.tripblog.portal.presentation.controllers;

import com.tripblog.portal.persistence.entities.Trip;
import com.tripblog.portal.persistence.entities.User;
import com.tripblog.portal.presentation.dto.TripDTO;
import com.tripblog.portal.presentation.dto.UserDTO;
import com.tripblog.portal.services.TripService;
import com.tripblog.portal.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TripDTO> getTrips() {
        return tripService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TripDTO getTripById(@PathVariable long id) {
        return convertToDTO(tripService.getById(id));
    }

    @PostMapping
    public TripDTO createTrip(@RequestBody TripDTO newTrip) {
        Trip trip = convertToEntity(newTrip);
        trip = tripService.create(trip);

        return convertToDTO(trip);
    }

    @PutMapping("/{id}")
    public TripDTO updateTrip(@PathVariable long id, @RequestBody TripDTO updateTrip) {
        Trip trip = convertToEntity(updateTrip);
        trip = tripService.update(id, trip);

        return convertToDTO(trip);
    }

    @DeleteMapping("/{id}")
    public TripDTO deleteTrip(@PathVariable long id) {
        return convertToDTO(tripService.delete(id));
    }

    @GetMapping("/{id}/user")
    public UserDTO getUser(@PathVariable long id) {
        Trip trip = tripService.getById(id);

        return convertToUserDTO(trip.getUser());
    }

    private TripDTO convertToDTO(Trip trip) {
        return modelMapper.map(trip, TripDTO.class);
    }

    private Trip convertToEntity(TripDTO dto) {
        return modelMapper.map(dto, Trip.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
