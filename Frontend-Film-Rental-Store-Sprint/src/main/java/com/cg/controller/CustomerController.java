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
		@ResponseBody
		public ResponseEntity<List< Customer>> searchCustomer(@RequestParam("firstName") String firstName) {
			try {
				List< Customer> customer = customerService.getCustomerByFirstName(firstName);
				return ResponseEntity.ok(customer);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
		// PUT request to update an actor's first name by actorId
		@PutMapping("update/{id}")
		public ResponseEntity<?> updateCustomerFirstName(
			@PathVariable("id") Integer customerId, 
			@RequestBody  Customer customerDTO) {
			try {
				 Customer updatedCustomer = customerService.updateCustomerByFirstName(customerId, customerDTO);
				return ResponseEntity.ok(updatedCustomer);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error updating actor: " + e.getMessage());
			}
		}
}