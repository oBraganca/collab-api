package com.userservice.controller;

import com.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.userservice.dto.Response;
import com.userservice.dto.UserDto;
import com.userservice.service.AuthenticationService;


@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDto userDto){
        try {
            Response response = authenticationService.authenticateUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Response.error(
                            "Invalid Email or Password",
                            HttpStatus.NOT_FOUND.value()
                    )
            );
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws Exception {

        try {
            Response response = authenticationService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Response.error(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        }
    }
}