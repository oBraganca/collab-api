package com.application.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.application.dto.Response;
import com.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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


@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;


 
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        try {
            Response response = userService.delete(id);

            return ResponseEntity.status(HttpStatus.OK).
                    body(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Response.error(
                            e.getMessage(),
                            HttpStatus.NOT_FOUND.value()
                    ));
        }
    }

	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
        try {
            Response response = userService.update(id, userDto);

            return ResponseEntity.status(HttpStatus.OK).
                    body(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Response.error(
                            e.getMessage(),
                            HttpStatus.NOT_FOUND.value()
                    ));
        }
    }
}