package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@SpringBootApplication
@RestController
public class CollabApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollabApiApplication.class, args);
	}
	
    @GetMapping("api/version")
    public ResponseEntity<Map<String, String>> getVersion() {
		Map<String, String> response = new HashMap<String, String>();
		response.put("version", "v0.0.1");

		return ResponseEntity.ok(response);
    }
}
