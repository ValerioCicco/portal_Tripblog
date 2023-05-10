package com.tripblog.portal.presentation.controllers;

import com.tripblog.portal.persistence.entities.Photo;
import com.tripblog.portal.persistence.entities.Trip;
import com.tripblog.portal.presentation.dto.PhotoDTO;
import com.tripblog.portal.presentation.dto.TripDTO;
import com.tripblog.portal.services.PhotoService;
import com.tripblog.portal.services.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "http://localhost:3000")
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private TripService tripService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PhotoDTO> getPhotos() {
        return photoService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public PhotoDTO getPhotoById(@PathVariable long id) {
        return convertToDTO(photoService.getById(id));
    }

    @PostMapping
    public PhotoDTO createPhoto(@RequestBody PhotoDTO newPhoto) {
        Photo photo = convertToEntity(newPhoto);
        photo = photoService.create(photo);

        return convertToDTO(photo);
    }

    @PutMapping("/{id}")
    public PhotoDTO updatePhoto(@PathVariable long id, @RequestBody PhotoDTO updatePhoto) {
        Photo photo = convertToEntity(updatePhoto);
        photo = photoService.update(id, photo);

        return convertToDTO(photo);
    }

    @DeleteMapping("/{id}")
    public PhotoDTO deletePhoto(@PathVariable long id) {
        return convertToDTO(photoService.delete(id));
    }

    @GetMapping("/{id}/trip")
    public TripDTO getTrip(@PathVariable long id) {
        Photo photo = photoService.getById(id);

        return convertToTripDTO(photo.getTrip());
    }

    private PhotoDTO convertToDTO(Photo photo) {
        return modelMapper.map(photo, PhotoDTO.class);
    }

    private Photo convertToEntity(PhotoDTO dto) {
        return modelMapper.map(dto, Photo.class);
    }

    private TripDTO convertToTripDTO(Trip trip) {
        return modelMapper.map(trip, TripDTO.class);
    }
}
