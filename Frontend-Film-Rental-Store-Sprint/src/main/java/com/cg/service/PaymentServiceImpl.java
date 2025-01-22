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
import org.springframework.web.client.RestTemplate;
 
import com.cg.model.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private RestTemplate restTemplate;
    private static final String PAYMENT_API_BASE_URL = "http://localhost:4311/api/payment";
 
    @Override
    public List<Payment> getRevenueByDate() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Payment[]> response = restTemplate.exchange(
            PAYMENT_API_BASE_URL + "/revenue/datewise",
            HttpMethod.GET,
            entity,
            Payment[].class
        );
        return Arrays.asList(response.getBody());
    }
    }