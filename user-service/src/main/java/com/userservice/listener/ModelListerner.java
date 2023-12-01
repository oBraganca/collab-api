package com.userservice.listener;

import java.time.LocalDateTime;

import com.userservice.model.BaseModel;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ModelListerner {
    @PrePersist
    public void prePersist(BaseModel entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(BaseModel entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
