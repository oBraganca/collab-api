package com.application.dto;

public class UserDto {
    private String name;
    private String username;
    private String email;
    private String password;
    
    
    public UserDto() {}
    
    public UserDto(String string, String string2, String string3, String string4) {
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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