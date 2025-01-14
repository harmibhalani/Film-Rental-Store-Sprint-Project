package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.RentalDTO;
import com.cg.service.RentalService;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/rental/customer/{id}")
    public ResponseEntity<List<RentalDTO>> getRentalsByCustomer(@PathVariable("id") Integer customerId) {
        List<RentalDTO> rentals = rentalService.getRentalsByCustomerId(customerId);
        return ResponseEntity.ok(rentals);
    }
}