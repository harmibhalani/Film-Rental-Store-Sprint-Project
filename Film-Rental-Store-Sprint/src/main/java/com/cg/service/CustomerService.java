package com.cg.service;

import java.util.List;

import com.cg.dto.CustomerCreateDTO;
import com.cg.dto.CustomerDTO;
import com.cg.exception.CustomerNotFoundException;
import com.cg.model.Address;

public interface CustomerService {
	
	//Search Customers by last name
		List<CustomerDTO> getAllCustomersByLastName(String lastName) throws CustomerNotFoundException;
		
		//Search Customers by first name
		List<CustomerDTO> getAllCustomersByFirstName(String firstName) throws CustomerNotFoundException;
		
		//Search Customers by email
		 List<CustomerDTO> getAllCustomersByEmail(String email) throws CustomerNotFoundException;
		
		 //Search Customers by City
		 List<CustomerDTO> getAllCustomersByCity(String city) throws CustomerNotFoundException;
		
		 //Search Customers by Country
		 List<CustomerDTO> getAllCustomersByCountry(String countryName) throws CustomerNotFoundException;
		
		 //Search all active Customers
		 List<CustomerDTO> getAllActiveCustomers()throws CustomerNotFoundException;
		
		//Search all Inactive Customers
		 List<CustomerDTO> getAllInactiveCustomers()throws CustomerNotFoundException;
		
		 //Search Customers by phone number
		 List<CustomerDTO> getAllCustomersByPhone(String phone)throws CustomerNotFoundException;
		
		 //Add new Customer Object in DB
		 CustomerDTO createCustomer(CustomerCreateDTO customerCreateDTO);
		
		 //Assign Address to a Customer
		 CustomerDTO updateCustomerAddress(Short customerId, Short addressId, Address newAddress)throws CustomerNotFoundException;
		
	     //Update first name of Customer
	     CustomerDTO updateCustomerFirstName(Short customerId, String newFirstName)throws CustomerNotFoundException;
		
	     //Update last name of Customer
	     CustomerDTO updateCustomerLastName(Short customerId, String newLastName)throws CustomerNotFoundException;
	     
	     //Update email of Customer
	     CustomerDTO updateCustomerEmail(Short customerId, String newEmail)throws CustomerNotFoundException;
	     
	     //Assign Store to a Customer
	     CustomerDTO updateCustomerStore(Short customerId, Short storeId)throws CustomerNotFoundException;
	     
	     //Update phone number of a Customer
	     CustomerDTO updateCustomerPhone(Short customerId, String phoneNumber)throws CustomerNotFoundException;
	 
}
