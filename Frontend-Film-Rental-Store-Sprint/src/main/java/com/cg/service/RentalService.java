package com.cg.service;
 
import java.util.List;
import com.cg.model.Rental;
 
public interface RentalService {
    List<Rental> getRentalsByCustomerId(Short customerId);
    Rental addRental(Rental rentalDTO);
    Rental updateReturnDate(Integer rentalId, String newReturnDate);
}
 
