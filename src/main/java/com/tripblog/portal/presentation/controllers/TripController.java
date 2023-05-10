package com.tripblog.portal.presentation.controllers;

import com.tripblog.portal.persistence.entities.Photo;
import com.tripblog.portal.persistence.entities.Trip;
import com.tripblog.portal.persistence.entities.User;
import com.tripblog.portal.presentation.dto.PhotoDTO;
import com.tripblog.portal.presentation.dto.TripDTO;
import com.tripblog.portal.presentation.dto.UserDTO;
import com.tripblog.portal.services.PhotoService;
import com.tripblog.portal.services.TripService;
import com.tripblog.portal.services.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trips")
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
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

    @GetMapping("/{id}/photos")
    public List<PhotoDTO> getPhotos(@PathVariable long id) {
        return photoService.getPhotosFromTrip(id)
                .stream()
                .map(this::convertToPhotoDTO)
                .toList();
    }

    @PostMapping(path = "/{id}/photos", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void savePhotos(@PathVariable long id, @RequestParam(name = "photo") List<MultipartFile> photos) throws IOException {
        for (MultipartFile photo : photos) {
            Photo newPhoto = new Photo();
            newPhoto.setTrip(tripService.getById(id));
            newPhoto.setPhoto(photo.getBytes());
            photoService.create(newPhoto);
        }
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

    private PhotoDTO convertToPhotoDTO(Photo photo) {
        return modelMapper.map(photo, PhotoDTO.class);
    }
}
