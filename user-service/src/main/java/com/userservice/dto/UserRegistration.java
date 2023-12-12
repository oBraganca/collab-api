package com.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRegistration(@NotEmpty( message = "First name not be null" ) String first_name,
                               @NotEmpty( message = "Last name not be null" ) String last_name,
                               @NotEmpty( message = "First name not be null" ) @Email( message = "Insert a valid email") String email,
                               @NotEmpty( message = "Password not be null" ) String password)  {
}
