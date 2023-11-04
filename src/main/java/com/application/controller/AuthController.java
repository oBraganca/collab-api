package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

import com.application.dto.Response;
import com.application.dto.UserDto;
import com.application.model.Role;
import com.application.model.User;
import com.application.model.UserCustomizations;
import com.application.repository.RoleRepository;
import com.application.repository.UserCustomizationsRepositoy;
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
    private UserCustomizationsRepositoy userCustomizationsRepositoy;
    
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

                Map<String, Object> dataResponse = new HashMap<>();
                
                dataResponse.put("first_name", user.getFirstName());
                dataResponse.put("last_name", user.getLastName());
                dataResponse.put("email", user.getEmail());
                
                Role role = user.getRole();
                dataResponse.put("role", role.getName());
                
                UserCustomizations userCustomizations = user.getUserCustomizations();
                dataResponse.put("picture",userCustomizations.getPicture());
                dataResponse.put("theme", userCustomizations.getTheme());

                dataResponse.put("token", token);

                Response response = new Response();
                response.setData(dataResponse);
                response.setMessage("User logged in successfully");
                response.setStatus(200);
                response.setSucess(true);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                Map<String, Object> dataResponse = new HashMap<>();
                errorResponse.put("data", dataResponse);
                errorResponse.put("status", 200);
                errorResponse.put("sucess", false);
                errorResponse.put("message", "Invalid email or password");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (AuthenticationException e) {
                Map<String, Object> errorResponse = new HashMap<>();
                Map<String, Object> dataResponse = new HashMap<>();
                errorResponse.put("data", dataResponse);
                errorResponse.put("status", 200);
                errorResponse.put("sucess", false);
                errorResponse.put("message", "Invalid email or password");
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
    
        UserCustomizations userCustomizations = new UserCustomizations();
        userCustomizationsRepositoy.save(userCustomizations);
        
        user.setUserCustomizations(userCustomizations);

        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
    
        userRepository.save(user);
    
        response.put("message", "User is registered successfully!"); // Add the "message" field
    
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}