package com.cg.service;
 
import java.time.LocalDateTime;
import java.util.List;
 
import com.cg.dto.RentalDTO;
 
public interface RentalService {
	// Display all Films rented to a customer
	List<RentalDTO> getRentalsByCustomerId(Short customerId);
    // Display top 10 most rented Films
	List<String> getTopTenFilms();
    // Display top 10 most rented Films of a Store
	 List<String> getTopTenFilmsByStoreId(Short storeId);
     // Display list of Customers who have not yet returned the  Film
	 List<RentalDTO> getDueRentalsByStoreId(Short storeId);
	 // Rent a  Film
	 RentalDTO addRental(RentalDTO rentalDTO);
	 // Update return date
	 RentalDTO updateReturnDate(Integer rentalId, LocalDateTime returnDate);
}