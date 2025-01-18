package com.cg.controller;
 
import java.time.LocalDateTime;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.exception.BadRequestException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.dto.RentalDTO;
import com.cg.dto.UpdateReturnDateDTO;
import com.cg.service.RentalService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api")
public class RentalController {
 
    @Autowired
    private RentalService rentalService;
 
 
    // Display all Films rented to a customer
    @GetMapping("/rental/customer/{id}")
    public ResponseEntity<List<RentalDTO>> getRentalsByCustomer(@PathVariable("id") Short customerId) {
        if (customerId == null || customerId <= 0) {
            throw new BadRequestException("Customer ID must be a positive number");
        }
        try {
            List<RentalDTO> rentals = rentalService.getRentalsByCustomerId(customerId);
            return ResponseEntity.ok(rentals);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BadRequestException("Error retrieving rentals for customer: " + ex.getMessage());
        }
    }
    // Display top 10 most rented Films
    @GetMapping("/rental/toptenfilms")
    public ResponseEntity<List<String>> getTopTenFilms() {
        try {
            List<String> topFilms = rentalService.getTopTenFilms();
            if (topFilms.isEmpty()) {
                throw new ResourceNotFoundException("No films found in the system");
            }
            return ResponseEntity.ok(topFilms);
        } catch (Exception ex) {
            throw new BadRequestException("Error retrieving top films: " + ex.getMessage());
        }
    }
     // Display top 10 most rented Films of a Store
    @GetMapping("/rental/toptenfilms/store/{id}")
    public ResponseEntity<List<String>> getTopTenFilmsByStoreId(@PathVariable("id") Short storeId) {
        if (storeId == null || storeId <= 0) {
            throw new BadRequestException("Store ID must be a positive number");
        }
        try {
            List<String> topFilms = rentalService.getTopTenFilmsByStoreId(storeId);
            if (topFilms.isEmpty()) {
                throw new ResourceNotFoundException("No films found for store ID: " + storeId);
            }
            return ResponseEntity.ok(topFilms);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BadRequestException("Error retrieving top films for store: " + ex.getMessage());
        }
    }
     // Display list of Customers who have not yet returned the  Film
    @GetMapping("/rental/due/store/{id}")
    public ResponseEntity<List<RentalDTO>> getDueRentalsByStoreId(@PathVariable("id") Short storeId) {
        if (storeId == null || storeId <= 0) {
            throw new BadRequestException("Store ID must be a positive number");
        }
        try {
            List<RentalDTO> dueRentals = rentalService.getDueRentalsByStoreId(storeId);
            if (dueRentals.isEmpty()) {
                throw new ResourceNotFoundException("No due rentals found for store ID: " + storeId);
            }
            return ResponseEntity.ok(dueRentals);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BadRequestException("Error retrieving due rentals for store: " + ex.getMessage());
        }
    }
    // Rent a  Film
    @PostMapping("/rental/add")
    public ResponseEntity<RentalDTO> addRental(@RequestBody @Valid RentalDTO rentalDTO , BindingResult bindingResult) {
        //validateRentalDTO(rentalDTO);

    	   if (bindingResult.hasErrors()) {
               String errorMessages = bindingResult.getAllErrors().stream()
                                                    .map(error -> error.getDefaultMessage())
                                                    .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                                                    .orElse("Validation failed");
               throw new BadRequestException(errorMessages);
           }

    	try {
            RentalDTO createdRental = rentalService.addRental(rentalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
        } catch (Exception ex) {
            throw new BadRequestException("Failed to create rental: " + ex.getMessage());
        }
    }
 
    // Update return date
    @PostMapping("/rental/update/returndate/{id}")
    public ResponseEntity<RentalDTO> updateReturnDate(
            @PathVariable("id") Integer rentalId, 
            @RequestBody UpdateReturnDateDTO updateReturnDateDTO) {
        if (rentalId == null || rentalId <= 0) {
            throw new BadRequestException("Rental ID must be a positive number");
        }
        if (updateReturnDateDTO == null || updateReturnDateDTO.getReturnDate() == null) {
            throw new BadRequestException("Return date is required");
        }
 
        try {
            LocalDateTime returnDate = LocalDateTime.parse(updateReturnDateDTO.getReturnDate());
            if (returnDate.isBefore(LocalDateTime.now())) {
                throw new BadRequestException("Return date cannot be in the past");
            }
            RentalDTO updatedRental = rentalService.updateReturnDate(rentalId, returnDate);
            return ResponseEntity.ok(updatedRental);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid date format: " + ex.getMessage());
        } catch (Exception ex) {
            throw new BadRequestException("Error updating return date: " + ex.getMessage());
        }
    }
}