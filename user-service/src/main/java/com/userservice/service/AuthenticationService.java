package com.userservice.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.userservice.model.Profile;
import com.userservice.repository.ProfileRepository;
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

import com.userservice.dto.Response;
import com.userservice.dto.UserDto;
import com.userservice.model.Role;
import com.userservice.model.User;
import com.userservice.repository.RoleRepository;
import com.userservice.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, ProfileRepository profileRepository,
                                PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    

    public Response authenticateUser(UserDto userDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authenticate.getPrincipal();
        Profile profile = user.getProfile();

        String token = tokenService.tokenGenerate(user);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("first_name", profile.getFirstName());
        dataResponse.put("last_name", profile.getLastName());
        dataResponse.put("email", user.getEmail());

        Role role = user.getRole();
        dataResponse.put("role", role.getName());

        dataResponse.put("picture", profile.getPicture());
        dataResponse.put("theme", profile.getTheme());

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
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    
        Profile profile = new Profile();

        profile.setFirstName(userDto.getFirst_name());
        profile.setLastName(userDto.getLast_name());

        profileRepository.save(profile);
        
        user.setProfile(profile);

        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
    
        userRepository.save(user);
    
        return new Response(Collections.emptyMap(), "User is registered successfully!", HttpStatus.CREATED.value(), true);

    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
    
}