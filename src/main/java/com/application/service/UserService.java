package com.application.service;

import com.application.dto.Response;
import com.application.dto.UserDto;
import com.application.model.Role;
import com.application.model.User;
import com.application.model.UserCustomizations;
import com.application.repository.UserRepository;
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
        existingUser.setFirstName(userDto.getFirst_name());

        // Save the updated user
        userRepository.save(existingUser);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("first_name", existingUser.getFirstName());
        dataResponse.put("last_name", existingUser.getLastName());
        dataResponse.put("email", existingUser.getEmail());

        Role role = existingUser.getRole();
        dataResponse.put("role", role.getName());

        UserCustomizations userCustomizations = existingUser.getUserCustomizations();
        dataResponse.put("picture", userCustomizations.getPicture());
        dataResponse.put("theme", userCustomizations.getTheme());

        return new Response(dataResponse, "User updated successfully", HttpStatus.OK.value(), true);
    }

}