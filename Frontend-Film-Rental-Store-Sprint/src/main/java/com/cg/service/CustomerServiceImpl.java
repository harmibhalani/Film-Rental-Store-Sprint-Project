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
	private static final String CUSTOMER_API_BASE_URL = "http://localhost:4311/api/customers";

	@Override
	public List<Customer> getCustomerByFirstName(String firstName) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Customer[]> response = restTemplate.exchange(
				CUSTOMER_API_BASE_URL + "/firstname/{fn}", 
                HttpMethod.GET,
                entity,
                Customer[].class, 
                firstName 
        );
        return Arrays.asList(response.getBody());
	}
	@Override
	public List<Customer> searchByLastName(String lastName) throws Exception {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        // Create HttpEntity with headers
	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        // Make a GET request using exchange method
	        ResponseEntity<Customer[]> response = restTemplate.exchange(
					CUSTOMER_API_BASE_URL + "/lastname/{fn}",
	                HttpMethod.GET,
	                entity,
	                Customer[].class,
	                lastName
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
	            		CUSTOMER_API_BASE_URL + "/update/fn/{id}",
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
	@Override
	public Customer updateCustomerByLastName(Integer customerId, Customer customer) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
	        try {
	            ResponseEntity<Customer> response = restTemplate.exchange(
	            		CUSTOMER_API_BASE_URL + "/update/ln/{id}",
	                HttpMethod.PUT,
	                entity,
	                Customer.class,
	                customerId
	            );
	            return response.getBody();
	        } catch (RestClientException e) {
	            throw new RuntimeException("Failed to update lastname: " + e.getMessage(), e);
	        }
	    }
}