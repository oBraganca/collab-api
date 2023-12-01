package com.userservice.dto;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Response {

    private boolean success;
    private int status;
    private String message;
    private Map<String, Object> data;

    public Response() {
        // Constructor
    }

    public Response(Map<String, Object> data, String message, int status, boolean success) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.success = success;
    }

    // Getters and setters

    public static Response error(String message, int status) {
        return new Response(Collections.emptyMap(), message, status, false);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
}
