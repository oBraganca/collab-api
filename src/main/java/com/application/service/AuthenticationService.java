package com.application.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.dto.Response;
import com.application.dto.UserDto;
import com.application.model.Role;
import com.application.model.User;
import com.application.model.UserCustomizations;
import com.application.repository.RoleRepository;
import com.application.repository.UserCustomizationsRepositoy;
import com.application.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserCustomizationsRepositoy userCustomizationsRepositoy;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, UserCustomizationsRepositoy userCustomizationsRepository,
                                PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userCustomizationsRepositoy = userCustomizationsRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    

    public Response authenticateUser(UserDto userDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authenticate.getPrincipal();

        String token = tokenService.tokenGenerate(user);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("first_name", user.getFirstName());
        dataResponse.put("last_name", user.getLastName());
        dataResponse.put("email", user.getEmail());

        Role role = user.getRole();
        dataResponse.put("role", role.getName());

        UserCustomizations userCustomizations = user.getUserCustomizations();
        dataResponse.put("picture", userCustomizations.getPicture());
        dataResponse.put("theme", userCustomizations.getTheme());

        dataResponse.put("token", token);

        return new Response(dataResponse, "User logged in successfully", HttpStatus.OK.value(), true);
    }

    public Response createUser(UserDto userDto ) throws Exception{

        Map<String, String> response = new HashMap<>();
    
        // checking for email exists in a database
        if (userRepository.existsByEmail(userDto.getEmail())) {
            if (userRepository.existsByEmail(userDto.getEmail())) {
                throw new Exception("Email is already in use.");
            }
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
    
        response.put("message", ""); // Add the "message" field
    
        return new Response(Collections.emptyMap(), "User is registered successfully!", HttpStatus.CREATED.value(), true);

    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
    
}