package com.cg.repositories;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.cg.model.Payment;
 
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	// Changed to return specific columns with aliases
	@Query(value = "SELECT DATE(payment_date) as payment_date, " + "COALESCE(SUM(amount), 0.00) as total_amount "
			+ "FROM payment " + "GROUP BY DATE(payment_date) " + "ORDER BY payment_date", nativeQuery = true)
	List<Object[]> findTotalRevenueByDate();
 
	
	@Query(value = "SELECT DATE(p.payment_date) as payment_date, " + "COALESCE(SUM(p.amount), 0.00) as total_amount "
			+ "FROM payment p " + "INNER JOIN staff s ON p.staff_id = s.staff_id " + "WHERE s.store_id = :storeId "
			+ "GROUP BY DATE(p.payment_date) " + "ORDER BY payment_date", nativeQuery = true)
	List<Object[]> findTotalRevenueByDateAndStore(@Param("storeId") Integer storeId);
 
	
	@Query(value = "SELECT f.title as film_title, " + "COALESCE(SUM(p.amount), 0.00) as total_revenue "
			+ "FROM payment p " + "INNER JOIN rental r ON p.rental_id = r.rental_id "
			+ "INNER JOIN inventory i ON r.inventory_id = i.inventory_id "
			+ "INNER JOIN film f ON i.film_id = f.film_id " + "GROUP BY f.film_id, f.title "
			+ "ORDER BY total_revenue DESC", nativeQuery = true)
	List<Object[]> findTotalRevenueByFilm();
 
	
	@Query(value = "SELECT " + "CONCAT(a.address, ', ', a.district, ' ', a.postal_code) as store_address, "
			+ "f.title as film_title, " + "COALESCE(SUM(p.amount), 0.00) as total_revenue " + "FROM payment p "
			+ "INNER JOIN staff s ON p.staff_id = s.staff_id " + "INNER JOIN store st ON s.store_id = st.store_id "
			+ "INNER JOIN address a ON st.address_id = a.address_id "
			+ "INNER JOIN rental r ON p.rental_id = r.rental_id "
			+ "INNER JOIN inventory i ON r.inventory_id = i.inventory_id "
			+ "INNER JOIN film f ON i.film_id = f.film_id " + "WHERE f.film_id = :filmId "
			+ "GROUP BY st.store_id, store_address, f.title " + "ORDER BY total_revenue DESC", nativeQuery = true)
	List<Object[]> findFilmRevenueByStore(@Param("filmId") Integer filmId);
 
	
	@Query(value = "SELECT " + "f.title as film_title, " + "COALESCE(SUM(p.amount), 0.00) as total_revenue, "
			+ "CONCAT('Store #', s.store_id, ' - ', " + "       a.address, ', ', a.district) as store_name "
			+ "FROM payment p " + "INNER JOIN staff s ON p.staff_id = s.staff_id "
			+ "INNER JOIN store st ON s.store_id = st.store_id "
			+ "INNER JOIN address a ON st.address_id = a.address_id "
			+ "INNER JOIN rental r ON p.rental_id = r.rental_id "
			+ "INNER JOIN inventory i ON r.inventory_id = i.inventory_id "
			+ "INNER JOIN film f ON i.film_id = f.film_id " + "WHERE s.store_id = :storeId "
			+ "GROUP BY f.film_id, f.title, store_name " + "ORDER BY total_revenue DESC", nativeQuery = true)
	List<Object[]> findFilmsRevenueByStore(@Param("storeId") Integer storeId);
 
}