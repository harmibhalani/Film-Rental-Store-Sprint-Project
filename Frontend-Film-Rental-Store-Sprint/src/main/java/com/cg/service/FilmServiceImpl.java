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
 
import com.cg.model.Film;
 
@Service
public class FilmServiceImpl implements FilmService{
	 @Autowired
	    private RestTemplate restTemplate;
 
	    private static final String FILM_API_BASE_URL = "http://localhost:4311/api/films";
	    
	    //Get all films
	    @Override
	    public List<Film> getAllFilms() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<String> entity = new HttpEntity<>(headers);
 
	        try {
	            ResponseEntity<Film[]> response = restTemplate.exchange(
	                FILM_API_BASE_URL + "/allfilms",
	                HttpMethod.GET,
	                entity,
	                Film[].class
	            );
 
	            if (response.getBody() == null) {
	                throw new RuntimeException("No films found");
	            }
 
	            return Arrays.stream(response.getBody())
	                .collect(Collectors.toList());
 
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to fetch all films: " + e.getMessage(), e);
	        }
	    }
	    
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