package com.userservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.userservice.model.User;
import com.userservice.model.UserCustomizations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserCustomizationsRepositoy extends JpaRepository<UserCustomizations, UUID> {

    Optional<UserCustomizations> findById(UUID id);
    
}