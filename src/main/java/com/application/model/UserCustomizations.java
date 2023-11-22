package com.application.model;

import java.util.UUID;

import com.application.listener.ModelListerner;

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
@Table(name="user_customizations")
@EnableAutoConfiguration
public class UserCustomizations extends BaseModel {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    
    @Column(nullable = true, name = "picture")
    private String picture;

    @Column(nullable = true, name = "theme")
    private Boolean theme;

    @Column(nullable = true, name = "location")
    private String location;

    @Column(nullable = true, name = "language")
    private String language;

    
    
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
