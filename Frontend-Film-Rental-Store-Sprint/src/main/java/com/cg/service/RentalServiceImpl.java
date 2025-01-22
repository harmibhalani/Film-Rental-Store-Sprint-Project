package com.cg.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 
import com.cg.model.Rental;
 
@Service
public class RentalServiceImpl implements RentalService {
 
    @Autowired
    private RestTemplate restTemplate;
 
    private static final String RENTAL_API_BASE_URL = "http://localhost:4311/api/rental";
 
    @Override
    public List<Rental> getRentalsByCustomerId(Short customerId) {
        // Replace with actual API call or database logic
        String url = RENTAL_API_BASE_URL + "/customer/" + customerId;
        List<Rental> rentals = restTemplate.exchange(url, HttpMethod.GET, null, List.class).getBody();
        return rentals;
    }
 
    @Override
    public Rental addRental(Rental rentalDTO) {
        String url = RENTAL_API_BASE_URL + "/add";
        Rental createdRental = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(rentalDTO), Rental.class).getBody();
        return createdRental;
    }
 
    @Override
    public Rental updateReturnDate(Integer rentalId, String newReturnDate) {
        String url = RENTAL_API_BASE_URL + "/update/returndate/" + rentalId;
        // Here we should handle the date format and send the new date
        Rental updatedRental = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(newReturnDate), Rental.class).getBody();
        return updatedRental;
    }
}