package com.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.application.listener.ModelListerner;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(ModelListerner.class)
@Table(name="users")
public class User extends BaseModel implements UserDetails{
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, name = "first_name")
    private String first_name;

    @Column(nullable = false, unique = true, name = "last_name")
    private String last_name;
    
    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = true, name = "picture")
    private String picture;

    @Column(nullable = false, name = "theme")
    private Boolean theme;

    @Column(nullable = false, name = "location")
    private String location;

    @Column(nullable = false, name = "language")
    private String language;

    
    @ManyToMany
    private Set<Role> roles;

    public UUID getId() {
        return id;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public Boolean getTheme() {
        return theme;
    }
    
    public void setTheme(Boolean theme) {
        this.theme = theme;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return last_name;
    }
    
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
    public String getFirstName() {
        return first_name;
    }
    
    public void getFirstName(String first_name) {
        this.first_name = first_name;
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
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public User orElseThrow(Object object) {
        return null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}