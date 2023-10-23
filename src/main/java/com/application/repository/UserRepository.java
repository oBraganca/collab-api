package com.application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.application.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);
    User getById(UUID id);
    
    @Transactional
    void deleteById(UUID id);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(UUID id);
}