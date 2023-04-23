package com.tripblog.portal.services;

import com.tripblog.portal.persistence.entities.User;
import com.tripblog.portal.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return optionalUser.get();
    }

    public User createUser(User newUser) {

        newUser = userRepository.save(newUser);

        return newUser;
    }

    public User updateUser(long id, User updateUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        User userToUpdate = optionalUser.get();

        updateUser.setId(userToUpdate.getId());

        updateUser = userRepository.save(updateUser);

        return updateUser;
    }

    public User deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        User userToDelete = optionalUser.get();

        userRepository.delete(userToDelete);

        return userToDelete;
    }

}
