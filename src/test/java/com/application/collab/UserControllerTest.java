package com.application.collab;

import com.application.controller.AuthController;
import com.application.controller.UserController;
import com.application.dto.UserDto;
import com.application.dto.UserDto;
import com.application.model.Role;
import com.application.model.User;
import com.application.repository.RoleRepository;
import com.application.repository.UserRepository;
import com.application.security.SecurityConfig;
import com.application.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNodes.JsonNode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Import(SecurityConfig.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String authToken;

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


    @BeforeEach
    void setUp() {
        // Chame o método para obter o token no início dos testes
        this.authToken = obtainAuthToken();
    }

    // Método para obter o token
    private String obtainAuthToken() {
        try {
            UserDto userDto = new UserDto();
            userDto.setUsername("newuser");
            userDto.setPassword("password123");
    
            User mockUser = new User();
            mockUser.setUsername("newuser");
            mockUser.setEmail("test@example.com");
    
            when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(mockUser, null, Collections.emptyList()));
            when(tokenService.tokenGenerate(any(User.class))).thenReturn("mockToken");
            when(userRepository.existsByUsername("testuser")).thenReturn(true);
    
            // Perform the POST request and expect a successful status code (200)
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                    .content(asJsonString(userDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            
            // Extrair o token do resultado
            String responseBody = result.getResponse().getContentAsString();
            com.fasterxml.jackson.databind.JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
            return "Bearer " + jsonNode.get("token").asText();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro ao obter o token de autenticação");
            return null;
        }
    }

    @Test
    @DisplayName("Update a user successfully")
    public void testUserUpdate() throws Exception {

        var user = userRepository.findByUsername("newuser");

        if(user != null){
            // Prepare test data
            UserDto userDto = new UserDto();
            userDto.setName("Test User Updated");
            userDto.setEmail("newuser@example.com");
    
            Role mockRole = new Role();
            mockRole.setName("ROLE_ADMIN");
    
            when(userRepository.existsByUsername("newuser")).thenReturn(true);
    
            // Perform the POST request and expect a successful status code (200)
            mockMvc.perform(MockMvcRequestBuilders.put("/user/" + user.getId())
                .header("Authorization", authToken)  // Set the Bearer token
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    @Test
    @DisplayName("Delete a user successfully")
    public void testUserDelete() throws Exception {

        var user = userRepository.findByUsername("newuser");

        if(user != null){
    
            Role mockRole = new Role();
            mockRole.setName("ROLE_ADMIN");
    
            when(userRepository.existsById(user.getId())).thenReturn(true);
    
            // Perform the POST request and expect a successful status code (200)
            mockMvc.perform(MockMvcRequestBuilders.put("/user/"+user.getId())
                    .header("Authorization", authToken)  // Set the Bearer token    
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
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