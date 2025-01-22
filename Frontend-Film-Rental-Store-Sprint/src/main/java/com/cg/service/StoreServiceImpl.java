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
import com.cg.model.Staff;
import com.cg.model.Store;
 
@Service
public class StoreServiceImpl implements StoreService{
 
	
	 @Autowired
	    private RestTemplate restTemplate;
	  private static final String FILM_API_BASE_URL = "http://localhost:4311/api/store";
	@Override
	public List<Store> getStoreByCityName(String city) throws Exception {
		 HttpHeaders headers = new org.springframework.http.HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 
	        // Create HttpEntity with headers
	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        // Make a GET request using exchange method
	        ResponseEntity<Store[]> response = restTemplate.exchange(
	        		FILM_API_BASE_URL + "/city/{cityName}", // URL of the API
	                HttpMethod.GET, // GET method
	                entity, // Request entity with headers
	                Store[].class, // Response type as Staff[]
	                city // Path variable for the title
	        );
 
	        // Convert the array of films into a list and return it
	        return Arrays.asList(response.getBody());
	}
 
	@Override
	public Store updateStorePhone(Short storeid, Store store) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
 
	        HttpEntity<Store> entity = new HttpEntity<>(store, headers);
 
	        try {
	            ResponseEntity<Store> response = restTemplate.exchange(
	            		FILM_API_BASE_URL + "/update/{storeId}",
	                HttpMethod.PUT,
	                entity,
	                Store.class,
	                storeid
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update firstname: " + e.getMessage(), e);
	        }
	    }

	}
 
 