package com.userservice.model;

import java.util.UUID;

import com.userservice.listener.ModelListerner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EntityListeners(ModelListerner.class)
@Table(name="profile")
@EnableAutoConfiguration
public class Profile extends BaseModel {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;


    @Column(nullable = false, unique = false, name = "first_name")
    private String first_name;

    @Column(nullable = false, unique = false, name = "last_name")
    private String last_name;

    @Column(nullable = true, name = "picture")
    private String picture;

    @Column(nullable = true, name = "theme")
    private Boolean theme;

    @Column(nullable = true, name = "location")
    private String location;

    @Column(nullable = true, name = "language")
    private String language;


    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getPicture() {
        return picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
