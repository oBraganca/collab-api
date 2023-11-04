package com.application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.application.model.User;
import com.application.model.UserCustomizations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserCustomizationsRepositoy extends JpaRepository<UserCustomizations, UUID> {

    Optional<UserCustomizations> findById(UUID id);
    
}