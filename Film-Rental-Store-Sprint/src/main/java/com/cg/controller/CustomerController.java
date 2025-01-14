package com.cg.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CustomerDTO;
import com.cg.model.Customer;
import com.cg.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/lastname/{ln}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByLastName(@PathVariable("ln") String lastName) {
        List<CustomerDTO> customers = customerService.getAllCustomersByLastName(lastName);
        
        // Convert List<Customer> to List<CustomerDTO>
        List<CustomerDTO> customerDTOs = customers.stream()
            .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(customerDTOs);
    }
}
