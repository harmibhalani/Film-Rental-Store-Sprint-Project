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
 
@Service
public class StaffServiceImpl implements StaffService {
 
	@Autowired
	private RestTemplate restTemplate;
 
	private static final String STAFF_API_BASE_URL = "http://localhost:4311/api/staff";
 
	@Override
	public List<Staff> searchByFirstName(String firstName) throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 
	        // Create HttpEntity with headers
	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        // Make a GET request using exchange method
	        ResponseEntity<Staff[]> response = restTemplate.exchange(
	        		STAFF_API_BASE_URL + "/firstname/{fn}", // URL of the API
	                HttpMethod.GET, // GET method
	                entity, // Request entity with headers
	                Staff[].class, // Response type as Staff[]
	                firstName // Path variable for the title
	        );
 
	        // Convert the array of films into a list and return it
	        return Arrays.asList(response.getBody());
	}
	@Override
	public List<Staff> searchByLastName(String lastName) throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 
	        // Create HttpEntity with headers
	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        // Make a GET request using exchange method
	        ResponseEntity<Staff[]> response = restTemplate.exchange(
	        		STAFF_API_BASE_URL + "/lastname/{ln}", // URL of the API
	                HttpMethod.GET, // GET method
	                entity, // Request entity with headers
	                Staff[].class, // Response type as Staff[]
	                lastName // Path variable for the title
	        );
 
	        // Convert the array of films into a list and return it
	        return Arrays.asList(response.getBody());
	}
 
	@Override
	public Staff updateStaffByFirstName(Short id, Staff staff) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
 
	        HttpEntity<Staff> entity = new HttpEntity<>(staff, headers);
 
	        try {
	            ResponseEntity<Staff> response = restTemplate.exchange(
	            		STAFF_API_BASE_URL + "/update/fn/{id}",
	                HttpMethod.PUT,
	                entity,
	                Staff.class,
	                id
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update firstname: " + e.getMessage(), e);
	        }
	    }
	@Override
	public Staff updateStaffByLastName(Short id, Staff staff) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
 
	        HttpEntity<Staff> entity = new HttpEntity<>(staff, headers);
 
	        try {
	            ResponseEntity<Staff> response = restTemplate.exchange(
	            		STAFF_API_BASE_URL + "/update/ln/{id}",
	                HttpMethod.PUT,
	                entity,
	                Staff.class,
	                id
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update lastname: " + e.getMessage(), e);
	        }
	    }
}