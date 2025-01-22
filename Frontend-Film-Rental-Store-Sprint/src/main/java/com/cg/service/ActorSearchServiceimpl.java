package com.cg.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cg.model.Actor;
import java.util.List;
 
@Service
public class ActorSearchServiceimpl implements ActorSearchService {
	private final String BASE_URL = "http://localhost:4311/api/actors/";
 
	@Autowired
	private RestTemplate restTemplate;
 
	public List<Actor> searchByLastName(String lastName) {
		ResponseEntity<List<Actor>> response = restTemplate.exchange(
				BASE_URL + "lastname/" + lastName,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Actor>>() {}
				);
		return response.getBody();
	}
 
	public List<Actor> searchByFirstName(String firstName) {
		ResponseEntity<List<Actor>> response = restTemplate.exchange(
				BASE_URL + "firstname/" + firstName,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Actor>>() {}
				);
		return response.getBody();
	}
 
	@Override
    public Actor searchFilmsByActorId(Integer actorId) {
        try {
            String url = BASE_URL + actorId + "/films";
            System.out.println("Calling URL: " + url); // For debugging
            ResponseEntity<Actor> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Actor>() {}
            );
            System.out.println("Response received: " + response.getBody()); // For debugging
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace(); // For debugging
            throw new RuntimeException("Error fetching actor details: " + e.getMessage());
        }
    }
}