package com.application.dto;

public class UserDto {
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    
    
    public UserDto() {}
    
    public UserDto(String first_name, String last_name, String username, String email, String password) {
    }

    public String getFirstName() {
        return first_name;
    }
    
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLastName() {
        return last_name;
    }
    
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}