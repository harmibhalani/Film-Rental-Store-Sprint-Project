package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.cg.model.User;
 
 
import java.util.Arrays;
import java.util.Optional;
 
@Service
public class UserService {
 
    @Autowired
    private RestTemplate restTemplate;
 
    private static final String USER_API_BASE_URL = "http://localhost:4311/api/users";
 
   
    public User registerUser(User user) {
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
 
        ResponseEntity<User> response = restTemplate.exchange(
                USER_API_BASE_URL + "/register", 
                HttpMethod.POST,
                entity,
                User.class
        );
 
        return response.getBody();  
    }
 
    // Validate user by making a GET request
    public Optional<User> validateUser(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
 
        ResponseEntity<User> response = restTemplate.exchange(
                USER_API_BASE_URL + "/validate/{username}/{password}", 
                HttpMethod.GET,
                entity,
                User.class,
                username, password
        );
 
        return Optional.ofNullable(response.getBody());  
    }
}   
