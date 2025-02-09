package com.cg.service;
import java.util.List;
import com.cg.model.Customer;
public interface CustomerService {
	List<Customer> getCustomerByFirstName(String firstName) throws Exception;
	Customer updateCustomerByFirstName(Integer customerId, Customer customer);
	List<Customer> searchByLastName(String lastName) throws Exception;
	 Customer updateCustomerByLastName(Integer customerId, Customer customer);
}