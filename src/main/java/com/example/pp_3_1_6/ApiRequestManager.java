package com.example.pp_3_1_6;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ApiRequestManager {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private String sessionId;


    public ApiRequestManager() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json");
        this.headers.set("Content-Type", "application/json");
    }

    public List<User> getUsers() {
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<User>>() {}
        );
        sessionId = response.getHeaders().getFirst("Set-Cookie");

        return response.getBody();
    }

    public String getSessionId() {
        return sessionId;
    }

    public ResponseEntity<String> createUser(String jsonData) {
        headers.set("Cookie", sessionId);
        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);
        return restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.POST,
                entity,
                String.class
        );
    }

    public ResponseEntity<String> updateUser(String jsonData) {
        headers.set("Cookie", sessionId);
        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);
        return restTemplate.exchange(
                "http://94.198.50.185:7081/api/users",
                HttpMethod.PUT,
                entity,
                String.class
        );
    }

    public ResponseEntity<String> deleteUser(String userId) {
        headers.set("Cookie", sessionId);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "http://94.198.50.185:7081/api/users/" + userId,
                HttpMethod.DELETE,
                entity,
                String.class
        );
    }

}
