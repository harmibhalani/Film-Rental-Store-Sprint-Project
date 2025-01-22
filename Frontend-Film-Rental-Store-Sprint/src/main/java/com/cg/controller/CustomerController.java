package com.cg.controller;
 
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.cg.model.Customer;
import com.cg.service.CustomerService;
 
 
@Controller
@RequestMapping("/homePage/dashboard/customerManagement")
public class CustomerController {
	 @Autowired
	    private CustomerService customerService;
	// GET request to search actors by first name
	 @GetMapping("/search-customer")
	    public ResponseEntity<?> searchCustomer(
	        @RequestParam String searchType,
	        @RequestParam String searchTerm) {
	        try {
	            List<Customer> results;
	            switch (searchType) {
	                case "firstname":
	                    results = customerService.getCustomerByFirstName(searchTerm);
	                    break;
	                case "lastname":
	                    results = customerService.searchByLastName(searchTerm);
	                    break;
	                default:
	                    return ResponseEntity.badRequest().body("Invalid search type");
	            }
	            return ResponseEntity.ok(results);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest()
	                .body("Error performing search: " + e.getMessage());
	        }
	    }
	
	 @PutMapping("/update/fn/{Id}")  // Changed from /fn/{id}
	 @ResponseBody
	 public ResponseEntity<?> updateCustomerFirstName(
	     @PathVariable("Id") Integer customerId,
@RequestBody Customer customerDTO) {
	     try {
	    	 Customer updatedCustomer = customerService.updateCustomerByFirstName(customerId, customerDTO);
	         return ResponseEntity.ok(updatedCustomer);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	             .body("Error updating firstname: " + e.getMessage());
	     }
	 }
	
	 @PutMapping("/update/ln/{Id}")  // Changed from /fn/{id}
	 @ResponseBody
	 public ResponseEntity<?> updateCustomerLastName(
	     @PathVariable("Id") Integer customerId,
@RequestBody Customer customerDTO) {
	     try {
	    	 Customer updatedCustomer = customerService.updateCustomerByLastName(customerId, customerDTO);
	         return ResponseEntity.ok(updatedCustomer);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	             .body("Error updating lastname: " + e.getMessage());
	     }
	 }
}
