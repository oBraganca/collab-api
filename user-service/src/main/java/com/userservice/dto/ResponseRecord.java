package com.userservice.dto;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

public record ResponseRecord(boolean success, int status, String message, Map<String, Object> data) {

    public ResponseRecord {
        // Initialization block
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
    }

    public static ResponseRecord error(String message, int status) {
        return new ResponseRecord(false, status, message, Collections.emptyMap());
    }
}
