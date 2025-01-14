package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.CustomerDTO;
import com.cg.model.Customer;
import com.cg.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAllCustomersByLastName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        
        // Convert List<Customer> to List<CustomerDTO>
        return customers.stream()
            .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
            .collect(Collectors.toList());
    }
}
