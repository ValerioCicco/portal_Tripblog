package com.tripblog.portal.services;

import com.tripblog.portal.persistence.entities.Photo;
import com.tripblog.portal.persistence.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAll() {
        return photoRepository.findAll();
    }

    public Photo getById(long id) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);

        if (optionalPhoto.isEmpty()) {
            throw new IllegalStateException("Photo not found");
        }

        return optionalPhoto.get();
    }

    public Photo create(Photo newPhoto) {

        if (newPhoto.getTrip() == null) {
            throw new IllegalStateException("Trip not found");
        }

        newPhoto = photoRepository.save(newPhoto);

        return newPhoto;
    }

    public Photo update(long id, Photo updatePhoto) {
        if (updatePhoto.getTrip() == null) {
            throw new IllegalStateException("Trip not found");
        }

        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        if (optionalPhoto.isEmpty()) {
            throw new IllegalStateException("Photo not found");
        }

        Photo photoToUpdate = optionalPhoto.get();

        updatePhoto.setId(photoToUpdate.getId());

        updatePhoto = photoRepository.save(updatePhoto);

        return updatePhoto;
    }

    public Photo delete(long id) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);

        if (optionalPhoto.isEmpty()) {
            throw new IllegalStateException("Photo not found");
        }

        Photo photoToDelete = optionalPhoto.get();

        photoRepository.delete(photoToDelete);

        return photoToDelete;
    }
}
