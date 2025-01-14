package com.cg.service;

import java.util.List;

import com.cg.dto.CustomerDTO;
import com.cg.model.Customer;

public interface CustomerService {
	
	List<CustomerDTO> getAllCustomersByLastName(String lastName);
}
