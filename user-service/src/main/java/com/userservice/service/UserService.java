package com.userservice.service;

import com.userservice.dto.Response;
import com.userservice.dto.UserDto;
import com.userservice.model.Profile;
import com.userservice.model.Role;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public Response delete (UUID id){

        var userExists = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        Map<String, Object> dataResponse = new HashMap<>();

        userRepository.deleteById(id);

        return new Response(dataResponse, "User deletion successfully", HttpStatus.OK.value(), true);
    }

    public Response update (UUID id, UserDto userDto){


        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        // Update user details based on userDto
        existingUser.getProfile().setFirstName(userDto.getFirst_name());

        // Save the updated user
        userRepository.save(existingUser);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("first_name", existingUser.getProfile().getFirstName());
        dataResponse.put("last_name", existingUser.getProfile().getLastName());
        dataResponse.put("email", existingUser.getEmail());

        Role role = existingUser.getRole();
        dataResponse.put("role", role.getName());

        Profile profile = existingUser.getProfile();
        dataResponse.put("picture", profile.getPicture());
        dataResponse.put("theme", profile.getTheme());

        return new Response(dataResponse, "User updated successfully", HttpStatus.OK.value(), true);
    }

}