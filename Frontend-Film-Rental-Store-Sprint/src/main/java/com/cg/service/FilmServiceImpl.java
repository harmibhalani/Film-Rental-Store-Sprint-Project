package com.cg.service;
 
import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
 
import com.cg.model.Film;
 
@Service
public class FilmServiceImpl implements FilmService{
	 @Autowired
	    private RestTemplate restTemplate;
 
//	    private static final String FILM_API_URL = "http://localhost:4311/api/films/title/{title}";
	    private static final String FILM_API_BASE_URL = "http://localhost:4311/api/films";
 
//	    @Override
//	    public List<Film> getFilmsByTitle(String title) {
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); 
//
//	        // Create HttpEntity with headers
//	        HttpEntity<String> entity = new HttpEntity<>(headers);
//	        
//	        
//	        // Make a GET request using exchange method
//	        ResponseEntity<Film[]> response = restTemplate.exchange(
//	        		FILM_API_BASE_URL + "/title/{title}", // URL of the API
//	                HttpMethod.GET, // GET method
//	                entity, // Request entity with headers
//	                Film[].class, // Response type as Film[]
//	                title // Path variable for the title
//	        );
//
//	        // Convert the array of films into a list and return it
//	        return Arrays.asList(response.getBody());
//	    }
	    
	  //Get method to search film by title
	    @Override
	    public List<Film> searchByTitle(String title) throws Exception {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<String> entity = new HttpEntity<>(headers);
 
	        ResponseEntity<Film[]> response = restTemplate.exchange(
	            FILM_API_BASE_URL + "/title/{title}",
	            HttpMethod.GET,
	            entity,
	            Film[].class,
	            title
	        );
 
	        return Arrays.asList(response.getBody());
	    }
	    
	  //Get method to search film by release year
	    @Override
	    public List<Film> searchByYear(Integer year) throws Exception {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<String> entity = new HttpEntity<>(headers);
 
	        ResponseEntity<Film[]> response = restTemplate.exchange(
	            FILM_API_BASE_URL + "/year/{year}",
	            HttpMethod.GET,
	            entity,
	            Film[].class,
	            year
	        );
 
	        return Arrays.asList(response.getBody());
	    }

	 // PUT method to update the film title
	    @Override
	    public Film updateFilmTitle(Integer id, Film film) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
 
	        HttpEntity<Film> entity = new HttpEntity<>(film, headers);
 
	        try {
	            ResponseEntity<Film> response = restTemplate.exchange(
	            		FILM_API_BASE_URL + "/update/title/{id}",
	                HttpMethod.PUT,
	                entity,
	                Film.class,
	                id
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update film: " + e.getMessage(), e);
	        }
	    }
	    
	    //PUT method to update release year of film
		@Override
		public Film updateByReleaseYear(Integer id, Film film) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
 
	        HttpEntity<Film> entity = new HttpEntity<>(film, headers);
 
	        try {
	            ResponseEntity<Film> response = restTemplate.exchange(
	            		FILM_API_BASE_URL + "/update/releaseyear/{id}",
	                HttpMethod.PUT,
	                entity,
	                Film.class,
	                id
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update release year: " + e.getMessage(), e);
	        }
		}
}