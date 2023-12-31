package com.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

import com.userservice.listener.ModelListerner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Entity
@EntityListeners(ModelListerner.class)
@Table(name="role")
@EnableAutoConfiguration
public class Role extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, name = "name")
    private String name;    
    
    public Role() {}    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}