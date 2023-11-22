package com.application.model;

import java.time.LocalDateTime;
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
@Table(name="post")
@EnableAutoConfiguration
public class Post extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, name = "description")
    private String description;

    public Post() {}    
    
}
