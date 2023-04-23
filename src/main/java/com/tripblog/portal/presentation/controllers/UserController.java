package com.tripblog.portal.presentation.controllers;

import com.tripblog.portal.persistence.entities.Trip;
import com.tripblog.portal.persistence.entities.User;
import com.tripblog.portal.presentation.dto.TripDTO;
import com.tripblog.portal.presentation.dto.UserDTO;
import com.tripblog.portal.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable long id) {
        return convertToDTO(userService.getById(id));
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO newUser) {
        User user = convertToEntity(newUser);

        user = userService.createUser(user);

        return convertToDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO updateUser) {
        User user = convertToEntity(updateUser);

        user = userService.updateUser(id, user);

        return convertToDTO(user);
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable long id) {
        return convertToDTO(userService.deleteUser(id));

    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    private TripDTO convertToTripDTO(Trip trip) {
        return modelMapper.map(trip, TripDTO.class);
    }
}
