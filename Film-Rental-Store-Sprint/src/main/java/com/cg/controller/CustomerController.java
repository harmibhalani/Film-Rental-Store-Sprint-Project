package com.cg.controller;
 
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.dto.CustomerCreateDTO;
import com.cg.dto.CustomerDTO;
import com.cg.exception.CustomerNotFoundException;
import com.cg.model.Address;
import com.cg.service.CustomerService;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
 
 
@RestController
@RequestMapping("/api")
public class CustomerController {
 
    @Autowired
    private CustomerService customerService;
  
    
   //Search Customers by last name
    @GetMapping("/customers/lastname/{ln}")
    public ResponseEntity<?> getCustomersByLastName(@PathVariable("ln") String lastName) {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomersByLastName(lastName);
            if (customers.isEmpty()) throw new CustomerNotFoundException("No customers found with last name: " + lastName);
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    //Search Customers by first name
    @GetMapping("/customers/firstname/{fn}")
    public ResponseEntity<?> getCustomersByFirstName(@PathVariable("fn") String firstName) {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomersByFirstName(firstName);
            if (customers.isEmpty()) throw new CustomerNotFoundException("No customers found with first name: " + firstName);
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
 
    //Search Customers by email  
    @GetMapping("/customers/email/{email}")
    public ResponseEntity<?> getCustomersByEmail(@PathVariable("email") @Email String email) {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomersByEmail(email);
            if (customers.isEmpty()) throw new CustomerNotFoundException("No customers found with email: " + email);
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
  
    //Search Customers by City
    @GetMapping("/customers/city/{city}")
    public ResponseEntity<?> getCustomersByCity(@PathVariable("city") String city) {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomersByCity(city);
            if (customers.isEmpty()) throw new CustomerNotFoundException("No customers found in city: " + city);
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    //Search Customers by Country
    @GetMapping("/customers/country/{country}")
    public ResponseEntity<?> getCustomersByCountry(@PathVariable("country") String country) {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomersByCountry(country);
            if (customers.isEmpty()) throw new CustomerNotFoundException("No customers found in country: " + country);
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
 
    //Search all active Customers
    @GetMapping("/customers/active")
    public ResponseEntity<?> getActiveCustomers() {
        try {
            List<CustomerDTO> activeCustomers = customerService.getAllActiveCustomers();
            if (activeCustomers.isEmpty()) throw new CustomerNotFoundException("No active customers found.");
            return ResponseEntity.ok(activeCustomers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    //Search all Inactive Customers
    @GetMapping("/customers/inactive")
    public ResponseEntity<?> getInactiveCustomers() {
        try {
            List<CustomerDTO> inactiveCustomers = customerService.getAllInactiveCustomers();
            if (inactiveCustomers.isEmpty()) throw new CustomerNotFoundException("No inactive customers found.");
            return ResponseEntity.ok(inactiveCustomers);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    //Search Customers by phone number
    @GetMapping("/customers/phone/{phone}")
    public ResponseEntity<?> getCustomersByPhone(@PathVariable("phone") String phone) {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomersByPhone(phone);
            if (customers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No customers found with phone number: " + phone);
            } else {
                return ResponseEntity.ok(customers);
            }
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An unexpected error occurred: " + e.getMessage());
        }
    }
 
    
   //Add new Customer Object in DB
    @PostMapping("/post")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerCreateDTO customerCreateDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerCreateDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
 
    // Assign Address to a Customer
    @PutMapping("/customers/{id}/address/{addressId}")
    public ResponseEntity<?> updateCustomerAddress(@PathVariable("id") Short customerId,
                                                   @PathVariable("addressId") Short addressId,
                                                   @RequestBody Address newAddress) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerAddress(customerId, addressId, newAddress);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating address: " + e.getMessage());
        }
    }
  
//// Update first name of Customer
    @PutMapping("/customers/update/fn/{id}")
    public ResponseEntity<?> updateCustomerFirstName(
            @PathVariable("id") Short customerId,
            @RequestBody Map<String, String> requestBody) {
        try {
            String newFirstName = requestBody.get("firstName");
            if (newFirstName == null || newFirstName.isBlank()) {
                return ResponseEntity.badRequest().body("First name is required");
            }
 
            CustomerDTO updatedCustomer = customerService.updateCustomerFirstName(customerId, newFirstName);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PutMapping("/customers/update/ln/{id}")
    public ResponseEntity<?> updateCustomerLastName(
            @PathVariable("id") Short customerId,
            @RequestBody Map<String, String> requestBody) {
        try {
            String newLastName = requestBody.get("lastName");
            if (newLastName == null || newLastName.isBlank()) {
                return ResponseEntity.badRequest().body("Last name is required");
            }
 
            CustomerDTO updatedCustomer = customerService.updateCustomerLastName(customerId, newLastName);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
  // Update email of Customer
    @PutMapping("/customers/update/{id}/email/{email}")
    public ResponseEntity<?> updateCustomerEmail(@PathVariable("id") Short customerId,
            @PathVariable("email") @Email String newEmail) {  
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerEmail(customerId, newEmail);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    // Update store of Customer
    @PutMapping("/customers/update/{id}/store/{storeId}")
    public ResponseEntity<?> updateCustomerStore(@PathVariable("id") Short customerId,
            @PathVariable("storeId") Short storeId) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomerStore(customerId, storeId);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
 
    // Update phone of Customer    
    @PutMapping("/customers/update/{id}/phone")
    public ResponseEntity<?> updateCustomerPhone(@PathVariable("id") Short customerId,
            @RequestBody Map<String, String> requestBody) {
        try {
            String phoneNumber = requestBody.get("phone");
            if(phoneNumber == null || phoneNumber.trim().isEmpty()) {  
                return ResponseEntity.badRequest().body("Phone number cannot be empty.");
            }
            CustomerDTO updatedCustomer = customerService.updateCustomerPhone(customerId, phoneNumber);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating phone number: " + e.getMessage());
        }
    }
}
