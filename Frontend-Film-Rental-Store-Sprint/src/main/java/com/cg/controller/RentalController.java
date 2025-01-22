package com.cg.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.model.Rental;
import com.cg.service.RentalService;
 
@RestController
@RequestMapping("/homePage/dashboard/rentalManagement")
public class RentalController {
 
    @Autowired
    private RentalService rentalService;
 
    // Get rentals by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Rental>> getRentalsByCustomer(@PathVariable("customerId") Short customerId) {
        try {
            List<Rental> rentals = rentalService.getRentalsByCustomerId(customerId);
            return ResponseEntity.ok(rentals);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
 
    // Add a rental
    @PostMapping("/add")
    public ResponseEntity<Rental> addRental(@RequestBody Rental rentalDTO) {
        try {
            Rental createdRental = rentalService.addRental(rentalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
 
    // Update return date
    @PostMapping("/update/returndate/{rentalId}")
    public ResponseEntity<Rental> updateReturnDate(@PathVariable("rentalId") Integer rentalId, @RequestBody String newReturnDate) {
        try {
            Rental updatedRental = rentalService.updateReturnDate(rentalId, newReturnDate);
            return ResponseEntity.ok(updatedRental);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

