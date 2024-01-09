package com.email.repositories;

import com.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

    EmailModel getByEmailFrom(String email);
}
