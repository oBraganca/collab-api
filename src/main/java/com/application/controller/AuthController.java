package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dto.UserDto;
import com.application.model.Role;
import com.application.model.User;
import com.application.repository.RoleRepository;
import com.application.repository.UserRepository;
import com.application.service.TokenService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if (authenticate != null && authenticate.isAuthenticated()) {
                var user = (User) authenticate.getPrincipal();

                String token = tokenService.tokenGenerate(user);

                Map<String, Object> response = new HashMap<>();
                response.put("token", token);

                return ResponseEntity.ok(response);
            } else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Authentication failed");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (AuthenticationException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Authentication failed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        Map<String, String> response = new HashMap<>();
    
        // checking for email exists in a database
        if (userRepository.existsByEmail(userDto.getEmail())) {
            response.put("message", "Email is already exist!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    
        // creating user object
        User user = new User();
        user.setFirstName(userDto.getFirst_name());
        user.setLastName(userDto.getLast_name());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(role);
    
        userRepository.save(user);
    
        response.put("message", "User is registered successfully!"); // Add the "message" field
    
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}