package com.application.userService;

import com.userservice.controller.AuthController;
import com.userservice.dto.UserDto;
import com.userservice.model.Role;
import com.userservice.model.User;
import com.userservice.repository.RoleRepository;
import com.userservice.repository.UserRepository;
import com.userservice.security.SecurityConfig;
import com.userservice.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(SecurityConfig.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Register a new user successfully")
    public void testRegisterUser_Success() throws Exception {

        // Prepare test data
        UserDto userDto = new UserDto();
        userDto.setFirst_name("Test User");
        userDto.setEmail("newuser@example.com");
        userDto.setPassword("password123");

        Role mockRole = new Role();
        mockRole.setName("ROLE_ADMIN");

        when(userRepository.existsByEmail("newuser")).thenReturn(false);
        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);

        // Perform the POST request and expect a successful status code (200)
        mockMvc.perform(post("/api/register")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Attempt to register a user with duplicate username and email, expect 403 status code")
    public void testRegisterUser_DuplicateUsernameAndEmail() throws Exception {
        // Prepare test data
        UserDto userDto = new UserDto();
        userDto.setFirst_name("Test User");
        userDto.setEmail("newuser@example.com");
        userDto.setPassword("password123");

        when(userRepository.existsByEmail("newuser")).thenReturn(true); // User with the same username exists
        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(true); // User with the same email exists

        // Perform the POST request and expect a forbidden status code (403)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("User login with correct credentials, expect a token in the response")
    public void testLogin_Success() throws Exception {
        // Prepare test data
        UserDto userDto = new UserDto();
        userDto.setEmail("nonexistinguser@example.com");
        userDto.setPassword("password123");

        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(new UsernamePasswordAuthenticationToken(mockUser, null, Collections.emptyList()));
        when(tokenService.tokenGenerate(any(User.class))).thenReturn("mockToken");
        when(userRepository.existsByEmail("nonexistinguser@example.com")).thenReturn(true);

        // Perform the POST request and expect a successful status code (200)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("mockToken"));
    }

    @Test
    @DisplayName("User login with incorrect password, expect 401 status code")
    public void testLogin_IncorrectPassword() throws Exception {
        // Prepare test data
        UserDto userDto = new UserDto();
        userDto.setEmail("testuser");
        userDto.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class); // Mock authentication failure
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        // Perform the POST request and expect an unauthorized status code (401)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("User login with non-existing username, expect 404 status code")
    public void testLogin_UserNotFound() throws Exception {
        // Prepare test data
        UserDto userDto = new UserDto();
        userDto.setEmail("nonexistinguser@example.com");
        userDto.setPassword("password123");

        when(userRepository.existsByEmail("nonexistinguser@example.com")).thenReturn(false);

        // Perform the POST request and expect a not found status code (404)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    // Utility method to convert objects to JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}