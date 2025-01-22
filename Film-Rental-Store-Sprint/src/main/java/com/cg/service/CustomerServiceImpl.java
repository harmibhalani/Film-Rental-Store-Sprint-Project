package com.cg.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dto.CustomerCreateDTO;
import com.cg.dto.CustomerDTO;
import com.cg.exception.CustomerNotFoundException;
import com.cg.model.Address;
import com.cg.model.Customer;
import com.cg.model.Store;
import com.cg.repositories.CustomerRepository;
import com.cg.repositories.StoreRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
 
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;
   
	@Autowired
	private StoreRepository storeRepository;
	
	
	//Search Customer by last name
	 @Override
	    public List<CustomerDTO> getAllCustomersByLastName(@NotBlank String lastName) throws CustomerNotFoundException {
	        System.out.println("Fetching customers with last name: " + lastName);
	        List<Customer> customers = customerRepository.findByLastName(lastName);
	        if (customers.isEmpty()) {
	            System.out.println("No customers found with last name: " + lastName);
	            throw new CustomerNotFoundException("No customers found with last name: " + lastName);
	        }
	        return customers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
	    
    //Search Customers by first name
	 @Override
	    public List<CustomerDTO> getAllCustomersByFirstName(@NotBlank String firstName) throws CustomerNotFoundException {
	        System.out.println("Fetching customers with first name: " + firstName);
	        List<Customer> customers = customerRepository.findByFirstName(firstName);
	        if (customers.isEmpty()) {
	            System.out.println("No customers found with first name: " + firstName);
	            throw new CustomerNotFoundException("No customers found with first name: " + firstName);
	        }
	        return customers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
	
    //Search Customers by email
	   @Override
	    public List<CustomerDTO> getAllCustomersByEmail(@Email String email) throws CustomerNotFoundException {
	        System.out.println("Fetching customers with email: " + email);
	        List<Customer> customers = customerRepository.findByEmail(email);
	        if (customers.isEmpty()) {
	            System.out.println("No customers found with email: " + email);
	            throw new CustomerNotFoundException("No customers found with email: " + email);
	        }
	        return customers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
	   
    //Search Customers by City
	   @Override
	    public List<CustomerDTO> getAllCustomersByCity(@NotBlank String city) throws CustomerNotFoundException {
	        System.out.println("Fetching customers in city: " + city);
	        List<Customer> customers = customerRepository.findByAddress_City_City(city);
	        if (customers.isEmpty()) {
	            System.out.println("No customers found in city: " + city);
	            throw new CustomerNotFoundException("No customers found in city: " + city);
	        }
	        return customers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
 
    //Search Customers by Country
	   @Override
	    public List<CustomerDTO> getAllCustomersByCountry(@NotBlank String countryName) throws CustomerNotFoundException {
	        System.out.println("Fetching customers in country: " + countryName);
	        List<Customer> customers = customerRepository.findByAddress_City_Country_Country(countryName);
	        if (customers.isEmpty()) {
	            System.out.println("No customers found in country: " + countryName);
	            throw new CustomerNotFoundException("No customers found in country: " + countryName);
	        }
	        return customers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
    
    //Search all active Customers
	   @Override
	    public List<CustomerDTO> getAllActiveCustomers() throws CustomerNotFoundException {
	        System.out.println("Fetching all active customers");
	        List<Customer> activeCustomers = customerRepository.findByActiveTrue();
	        if (activeCustomers.isEmpty()) {
	            System.out.println("No active customers found.");
	            throw new CustomerNotFoundException("No active customers found.");
	        }
	        return activeCustomers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
        
      //Search all Inactive Customers
	   @Override
	    public List<CustomerDTO> getAllInactiveCustomers() throws CustomerNotFoundException {
	        System.out.println("Fetching all inactive customers");
	        List<Customer> inactiveCustomers = customerRepository.findByActiveFalse();
	        if (inactiveCustomers.isEmpty()) {
	            System.out.println("No inactive customers found.");
	            throw new CustomerNotFoundException("No inactive customers found.");
	        }
	        return inactiveCustomers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
	   
        //Search Customers by phone number
	   @Override
	    public List<CustomerDTO> getAllCustomersByPhone(@NotBlank @jakarta.validation.constraints.Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits.") String phone) throws CustomerNotFoundException {
	        System.out.println("Fetching customers with phone number: " + phone);
	        List<Customer> customers = customerRepository.findByAddress_Phone(phone);
	        if (customers.isEmpty()) {
	            System.out.println("No customers found with phone number: " + phone);
	            throw new CustomerNotFoundException("No customers found with phone number: " + phone);
	        }
	        return customers.stream()
	                .map(customer -> new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail()))
	                .collect(Collectors.toList());
	    }
 
	   //Add new Customer Object in DB
       @Override
       public CustomerDTO createCustomer(@Valid CustomerCreateDTO customerCreateDTO) {
            Address address = customerRepository.findAddressById(customerCreateDTO.getAddress_id())
                    .orElseThrow(() -> new CustomerNotFoundException("Address not found"));
            Store store = storeRepository.findById(customerCreateDTO.getStore_id())
                    .orElseThrow(() -> new CustomerNotFoundException("Store not found"));
            Customer customer = new Customer();
            customer.setFirstName(customerCreateDTO.getFirst_name());
            customer.setLastName(customerCreateDTO.getFirst_name());
            customer.setEmail(customerCreateDTO.getEmail());
            customer.setAddress(address);
            customer.setStore(store);     
            Customer savedCustomer = customerRepository.save(customer);
            return new CustomerDTO(savedCustomer.getCustomerId(), savedCustomer.getFirstName(), savedCustomer.getLastName(), savedCustomer.getEmail());
        }
	   
 
	   
	//Assign Address to a Customer
	   @Override
	    public CustomerDTO updateCustomerAddress(Short customerId, Short addressId, @Valid Address newAddress) throws CustomerNotFoundException {
	        System.out.println("Updating address for customer ID: " + customerId + ", Address ID: " + addressId);
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
	        if (customer.getAddress() != null && customer.getAddress().getAddressId().equals(addressId)) {
	            customer.getAddress().setAddress(newAddress.getAddress());
	            customer.getAddress().setDistrict(newAddress.getDistrict());
	            customer.getAddress().setPostalCode(newAddress.getPostalCode());
	            customer.getAddress().setPhone(newAddress.getPhone());
	        } else {
	            System.out.println("Address not found for this customer");
	            throw new RuntimeException("Address not found for this customer");
	        }
	        Customer updatedCustomer = customerRepository.save(customer);
	        System.out.println("Address updated for customer ID: " + customerId);
 
	        return new CustomerDTO(updatedCustomer.getCustomerId(), updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getEmail());
	    }
	   
	    //Update Customers by first name
	   @Override
	    public CustomerDTO updateCustomerFirstName(Short customerId, String newFirstName) throws CustomerNotFoundException {
	        System.out.println("Updating first name for customer ID: " + customerId);

	        // Find the customer by ID
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

	        // Update the first name
	        customer.setFirstName(newFirstName);
	        Customer updatedCustomer = customerRepository.save(customer);

	        System.out.println("First name updated for customer ID: " + customerId);

	        // Return updated customer details as DTO
	        return new CustomerDTO(
	                updatedCustomer.getCustomerId(),
	                updatedCustomer.getFirstName(),
	                updatedCustomer.getLastName(),
	                updatedCustomer.getEmail()
	        );
	    }

  
	    //Update last name of Customer
//Update last name of Customer
	   
	   @Override
	    public CustomerDTO updateCustomerLastName(Short customerId, String newLastName) throws CustomerNotFoundException {
	        System.out.println("Updating last name for customer ID: " + customerId);
 
	        // Find the customer by ID
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
 
	        // Update the first name
	        customer.setLastName(newLastName);
	        Customer updatedCustomer = customerRepository.save(customer);
 
	        System.out.println("last name updated for customer ID: " + customerId);
 
	        // Return updated customer details as DTO
	        return new CustomerDTO(
	                updatedCustomer.getCustomerId(),
	                updatedCustomer.getFirstName(),
	                updatedCustomer.getLastName(),
	                updatedCustomer.getEmail()
	        );
	    }
	    
	    //Update email of Customer
        @Override
	    public CustomerDTO updateCustomerEmail(Short customerId, String newEmail) throws CustomerNotFoundException{
	    	 System.out.println("Updating email for customer ID: " + customerId);
	    	Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new RuntimeException("Customer not found"));
	        customer.setEmail(newEmail);
	        Customer updatedCustomer = customerRepository.save(customer);
	        System.out.println("Email updated for customer ID: " + customerId);
	        return new CustomerDTO(
	                updatedCustomer.getCustomerId(),
	                updatedCustomer.getFirstName(),
	                updatedCustomer.getLastName(),
	                updatedCustomer.getEmail()
	        );
	    }
	    
	    //Assign Store to a Customer
	        @Override
	        public CustomerDTO updateCustomerStore(Short customerId, Short storeId) throws CustomerNotFoundException {
	            System.out.println("Updating store for customer ID: " + customerId);
	            Customer customer = customerRepository.findById(customerId)
	                    .orElseThrow(() -> new RuntimeException("Customer not found"));
 
	            Store store = storeRepository.findById(storeId)
	                    .orElseThrow(() -> new RuntimeException("Store not found"));
 
	            customer.setStore(store);
 
	            Customer updatedCustomer = customerRepository.save(customer);
	            System.out.println("Store updated for customer ID: " + customerId);
 
	            return new CustomerDTO(
	                    updatedCustomer.getCustomerId(),
	                    updatedCustomer.getFirstName(),
	                    updatedCustomer.getLastName(),
	                    updatedCustomer.getEmail()
	            );
	        }
 
		//Update phone number of a Customer    
	        @Override
	        public CustomerDTO updateCustomerPhone(Short customerId,
	                                               @NotBlank @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits.") String phoneNumber)
	                                               throws CustomerNotFoundException {
	            System.out.println("Updating phone number for customer ID: " + customerId);
	            Customer customer = customerRepository.findById(customerId)
	                    .orElseThrow(() -> new RuntimeException("Customer not found"));
 
	            customer.getAddress().setPhone(phoneNumber);
 
	            Customer updatedCustomer = customerRepository.save(customer);
	            System.out.println("Phone number updated for customer ID: " + customerId);
 
	            return new CustomerDTO(
	                    updatedCustomer.getCustomerId(),
	                    updatedCustomer.getFirstName(),
	                    updatedCustomer.getLastName(),
	                    updatedCustomer.getEmail()
	            );
	        }
	}