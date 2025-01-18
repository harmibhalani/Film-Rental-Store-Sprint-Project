package com.cg.repositories;
 
import java.time.LocalDateTime;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.cg.model.Rental;
 
@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
	// Display all Films rented to a customer
    List<Rental> findByCustomerId(Short customerId); 
    // Display list of Customers who have not yet returned the  Film
    @Query(value = "SELECT * FROM rental WHERE inventory_id IN (SELECT inventory_id FROM inventory WHERE store_id = ?1) AND return_date < ?2", nativeQuery = true)
    List<Rental> findDueRentalsByStoreId(Short storeId, LocalDateTime currentDate); // Adjust as needed
 
    // Display top 10 most rented Films of a Store
    @Query(value = "SELECT inventory_id, COUNT(inventory_id) as rental_count FROM rental WHERE inventory_id IN (SELECT inventory_id FROM inventory WHERE store_id = ?1) GROUP BY inventory_id ORDER BY rental_count DESC LIMIT 10", nativeQuery = true)
    List<String> findTopTenFilmsByStoreId(Short storeId);
 
    // Display top 10 most rented Films
    @Query(value = "SELECT inventory_id, COUNT(inventory_id) as rental_count FROM rental GROUP BY inventory_id ORDER BY rental_count DESC LIMIT 10", nativeQuery = true)
    List<String> findTopTenFilms();

 
    
}