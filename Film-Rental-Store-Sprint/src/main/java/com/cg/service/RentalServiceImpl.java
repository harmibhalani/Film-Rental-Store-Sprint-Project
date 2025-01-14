package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.RentalDTO;
import com.cg.model.Rental;
import com.cg.repositories.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public List<RentalDTO> getRentalsByCustomerId(Integer customerId) {
        List<Rental> rentals = rentalRepository.findByCustomerId(customerId);
        
        // Convert List<Rental> to List<RentalDTO>
        return rentals.stream()
            .map(rental -> new RentalDTO(
                rental.getRentalId(),
                rental.getRentalDate(),
                rental.getReturnDate(),
                rental.getInventoryId(),
                rental.getStaffId()))
            .collect(Collectors.toList());
    }
}