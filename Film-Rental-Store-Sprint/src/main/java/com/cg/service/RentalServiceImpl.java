package com.cg.service;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.exception.ResourceNotFoundException;
import com.cg.dto.RentalDTO;
import com.cg.model.Rental;
import com.cg.repositories.RentalRepository;
 
@Service
public class RentalServiceImpl implements RentalService {
 
    @Autowired
    private RentalRepository rentalRepository;
 
// Display all Films rented to a customer
    @Override
    public List<RentalDTO> getRentalsByCustomerId(Short customerId) {
        List<Rental> rentals = rentalRepository.findByCustomerId(customerId);
        if (rentals.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return rentals.stream()
                .map(rental -> new RentalDTO(
                    rental.getRentalId(),
                    rental.getRentalDate(),
                    rental.getReturnDate(),
                    rental.getInventoryId(),
                    rental.getStaffId(),
                    rental.getCustomerId())) 
                .collect(Collectors.toList());
    }
    // Display top 10 most rented Films
    @Override
    public List<String> getTopTenFilms() {
        return rentalRepository.findTopTenFilms();
    }
    // Display top 10 most rented Films of a Store
    @Override
    public List<String> getTopTenFilmsByStoreId(Short storeId) {
        return rentalRepository.findTopTenFilmsByStoreId(storeId); 
    }
    // Display list of Customers who have not yet returned the  Film
    @Override
    public List<RentalDTO> getDueRentalsByStoreId(Short storeId) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Rental> dueRentals = rentalRepository.findDueRentalsByStoreId(storeId, currentDate);
        return dueRentals.stream()
                .map(rental -> new RentalDTO(
                    rental.getRentalId(),
                    rental.getRentalDate(),
                    rental.getReturnDate(),
                    rental.getInventoryId(),
                    rental.getStaffId(),  
                    rental.getCustomerId())) 
                .collect(Collectors.toList());
    }
 
	 // Rent a  Film
    @Override
    public RentalDTO addRental(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setInventoryId(rentalDTO.getInventoryId());
        rental.setCustomerId(rentalDTO.getCustomerId());
        rental.setStaffId(rentalDTO.getStaffId());
 
        Rental savedRental = rentalRepository.save(rental);
 
        return new RentalDTO(
                savedRental.getRentalId(),
                savedRental.getRentalDate(),
                savedRental.getReturnDate(),
                savedRental.getInventoryId(),
                savedRental.getStaffId(),
                savedRental.getCustomerId());
    }
	 // Update return date
    @Override
    public RentalDTO updateReturnDate(Integer rentalId, LocalDateTime returnDate) {
        Rental existingRental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new ResourceNotFoundException());
        existingRental.setReturnDate(returnDate); 

        Rental updatedRental = rentalRepository.save(existingRental);
 
      
        return new RentalDTO(
            updatedRental.getRentalId(),
            updatedRental.getRentalDate(),
            updatedRental.getReturnDate(),
            updatedRental.getInventoryId(),
            updatedRental.getStaffId(),
            updatedRental.getCustomerId());
    }
 
    
}
