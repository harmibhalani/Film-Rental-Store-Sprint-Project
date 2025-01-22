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
	public List<Staff> getStaffByFirstName(String firstName) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<Staff[]> response = restTemplate.exchange(STAFF_API_BASE_URL + "/firstname/{fn}", HttpMethod.GET,
				entity, Staff[].class, firstName);

		return Arrays.asList(response.getBody());
	}

	@Override
	public Staff updateStaffByFirstName(Integer staffId, Staff staff) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Staff> entity = new HttpEntity<>(staff, headers);

		try {
			ResponseEntity<Staff> response = restTemplate.exchange(STAFF_API_BASE_URL + "/update/fn/{id}",
					HttpMethod.PUT, entity, Staff.class, staffId);
			return response.getBody();
		} catch (RestClientException e) {
			throw new RuntimeException("Failed to update staff first name: " + e.getMessage(), e);
		}
	}
}
