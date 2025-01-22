package com.cg.service;
 
import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.cg.model.Customer;
 
@Service
public class CustomerServiceImpl implements CustomerService{
 
	
	@Autowired
	private RestTemplate restTemplate;
 
	private static final String ACTOR_API_BASE_URL = "http://localhost:4311/api/customers";
 
	
	@Override
	public List<Customer> getCustomerByFirstName(String firstName) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
 
		ResponseEntity<Customer[]> response = restTemplate.exchange(
				ACTOR_API_BASE_URL + "/firstname/{fn}", 
                HttpMethod.GET,
                entity,
                Customer[].class, 
                firstName 
        );
 
        return Arrays.asList(response.getBody());
	}
 
	
 
	@Override
	public Customer updateCustomerByFirstName(Integer customerId, Customer customer) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
 
	        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
	        try {
	            ResponseEntity<Customer> response = restTemplate.exchange(
	            		ACTOR_API_BASE_URL + "/update/{id}",
	                HttpMethod.PUT,
	                entity,
	                Customer.class,
	                customerId
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update actor first name: " + e.getMessage(), e);
	        }
	}
 
}