package com.cg.service;
 
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
 
import com.cg.model.Actor;
import com.cg.model.Film;
 
@Service
public class ActorServiceImpl implements ActorService {
 
    @Autowired
    private RestTemplate restTemplate;
 
    private static final String ACTOR_API_BASE_URL = "http://localhost:4311/api/actors";
 
    @Override
    public List<Actor> searchByFirstName(String firstName) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
 
        ResponseEntity<Actor[]> response = restTemplate.exchange(
            ACTOR_API_BASE_URL + "/firstname/{fn}",
            HttpMethod.GET,
            entity,
            Actor[].class,
            firstName
        );
 
        return Arrays.asList(response.getBody());
    }
 
    @Override
    public List<Actor> searchByLastName(String lastName) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
 
        ResponseEntity<Actor[]> response = restTemplate.exchange(
            ACTOR_API_BASE_URL + "/lastname/{ln}",
            HttpMethod.GET,
            entity,
            Actor[].class,
            lastName
        );
 
        return Arrays.asList(response.getBody());
    }
 
    @Override
    public Actor updateActorFirstName(Integer actorId, Actor actor) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
 
        HttpEntity<Actor> entity = new HttpEntity<>(actor, headers);
 
        try {
            ResponseEntity<Actor> response = restTemplate.exchange(
                ACTOR_API_BASE_URL + "/update/firstname/{id}",
                HttpMethod.PUT,
                entity,
                Actor.class,
                actorId
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to update actor first name: " + e.getMessage(), e);
        }
    }
 
    @Override
    public Actor updateActorLastName(Integer actorId, Actor actor) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
 
        HttpEntity<Actor> entity = new HttpEntity<>(actor, headers);
 
        try {
            ResponseEntity<Actor> response = restTemplate.exchange(
                ACTOR_API_BASE_URL + "/update/lastname/{id}",
                HttpMethod.PUT,
                entity,
                Actor.class,
                actorId
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to update actor last name: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Actor createActor(Actor actorDTO) {
        // Check if the actor already exists by first name and last name
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
 
        // Making a GET request to check if an actor with the same name exists
//        ResponseEntity<Actor[]> response = restTemplate.exchange(
//                ACTOR_API_BASE_URL + "/firstname/{fn}/lastname/{ln}", 
//                HttpMethod.GET,
//                entity,
//                Actor[].class, 
//                actorDTO.getFirstName(),
//                actorDTO.getLastName()
//        );
 
        // If actor already exists, return a response with a message
//        if (response.getBody() != null && response.getBody().length > 0) {
//            throw new RuntimeException("Actor already exists with the same name.");
//        }
 
        // If no actor exists, proceed to create the new actor
        actorDTO.setActorId(null);  // Ensure that no actor ID is pre-set (it will be generated on creation)
 
        // Make a POST request to create the actor
        HttpEntity<Actor> postEntity = new HttpEntity<>(actorDTO, headers);
        try {
            ResponseEntity<Actor> postResponse = restTemplate.exchange(
                    ACTOR_API_BASE_URL + "/post", 
                    HttpMethod.POST, 
                    postEntity, 
                    Actor.class
            );
 
            return postResponse.getBody();  // Return the saved actor
 
        } catch (RestClientException e) {
            throw new RuntimeException("Error creating actor: " + e.getMessage(), e);
        }
    }
    
    //Get all actors
    @Override
    public List<Actor> getAllActors() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
 
        try {
            ResponseEntity<Actor[]> response = restTemplate.exchange(
                ACTOR_API_BASE_URL + "/allactors",
                HttpMethod.GET,
                entity,
                Actor[].class
            );
 
            if (response.getBody() == null) {
                throw new RuntimeException("No actors found");
            }
 
            return Arrays.stream(response.getBody())
                .collect(Collectors.toList());
 
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch all actors: " + e.getMessage(), e);
        }
    }
}