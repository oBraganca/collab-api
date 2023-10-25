package com.application.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.UserDto;
import com.application.model.User;
import com.application.repository.UserRepository;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
			var userExists = userRepository.existsById(id);
			Map<String, String> response = new HashMap<>();
			if(userExists){
				response.put("message", "User deletion successfully");
	
				userRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			response.put("message", "User dont exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User deletion failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
		try {
            User existingUser = userRepository.findById(id).orElse(null);
            if (existingUser == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Update user details based on userDto
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setEmail(userDto.getEmail());

            // Save the updated user
            userRepository.save(existingUser);

			Map<String, String> response = new HashMap<>();
            response.put("error", "User update successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User update failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}