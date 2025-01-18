package com.cg.controller;
 
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.dto.FilmRevenueDTO;
import com.cg.dto.FilmStoreRevenueDTO;
import com.cg.dto.PaymentDTO;
import com.cg.dto.AddPaymentRequestDTO;
import com.cg.dto.StoreFilmRevenueDTO;
import com.cg.exception.PaymentNotFoundException;
import com.cg.service.PaymentService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api")
@Validated
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
 
	@Autowired
 
	private PaymentService paymentService;
 
//    Calculate Cumulative revenue of all Stores
	@GetMapping("/payment/revenue/datewise")
	public ResponseEntity<?> getPaymentRevenueByDate() {
		logger.info("Received request for date-wise payment revenue");
		List<PaymentDTO> revenueList = paymentService.getRevenueByDate();
		if (revenueList.isEmpty()) {
			logger.warn("No payment data found");
			// return ResponseEntity.noContent().build();
			// throw new PaymentNotFoundException("No payment data found for the given date
			// range.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No payment data found for the given date range.");
		}
		logger.info("Returning {} payment records", revenueList.size());
		return ResponseEntity.ok(revenueList);
	}
 
//    Calculate Cumulative revenue of a Store
	@GetMapping("/payment/revenue/datewise/store/{id}")
	public ResponseEntity<List<PaymentDTO>> getStoreRevenueByDate(@PathVariable("id") Integer storeId) {
		logger.info("Received request for date-wise payment revenue for store ID: {}", storeId);
		List<PaymentDTO> storeRevenue = paymentService.getRevenueByDateForStore(storeId);
 
		if (storeRevenue.isEmpty()) {
			logger.warn("No payment data found for store ID: {}", storeId);
			// return ResponseEntity.noContent().build();
			throw new PaymentNotFoundException("No payment data found for store ID: " + storeId);
		}
 
		logger.info("Returning {} payment records for store ID: {}", storeRevenue.size(), storeId);
		return ResponseEntity.ok(storeRevenue);
	}
 
//    Calculate Cumulative revenue of all Films (across all  stores)
	@GetMapping("/payment/revenue/filmwise")
	public ResponseEntity<List<FilmRevenueDTO>> getFilmwiseRevenue() {
		logger.info("Received request for film-wise revenue calculation");
		List<FilmRevenueDTO> filmRevenue = paymentService.getRevenueByFilm();
 
		if (filmRevenue.isEmpty()) {
			logger.warn("No film revenue data found");
			// return ResponseEntity.noContent().build();
			throw new PaymentNotFoundException("No film revenue data found.");
		}
 
		logger.info("Returning revenue data for {} films", filmRevenue.size());
		return ResponseEntity.ok(filmRevenue);
 
	}
 
//   Calculate Cumulative revenue of a Film Store wise
	@GetMapping("/payment/revenue/film/{id}")
	public ResponseEntity<List<FilmStoreRevenueDTO>> getFilmRevenueByStore(@PathVariable("id") Integer filmId) {
		logger.info("Received request for store-wise revenue calculation for film IDs: {}", filmId);
 
		List<FilmStoreRevenueDTO> storeRevenue = paymentService.getFilmRevenueByStore(filmId);
 
		if (storeRevenue.isEmpty()) {
			logger.warn("No revenue data found for film ID: {}", filmId);
			// return ResponseEntity.noContent().build();
			throw new PaymentNotFoundException("No revenue data found for film ID: " + filmId);
		}
 
		logger.info("Returning revenue data for film ID: {} across {} stores", filmId, storeRevenue.size());
		return ResponseEntity.ok(storeRevenue);
	}
 
//  Calculate Cumulative revenue of all Films by Store
	@GetMapping("/payment/revenue/films/store/{id}")
	public ResponseEntity<List<StoreFilmRevenueDTO>> getFilmsRevenueByStore(@PathVariable("id") Integer storeId) {
		logger.info("Received request for film-wise revenue calculation for store ID: {}", storeId);
 
		List<StoreFilmRevenueDTO> filmsRevenue = paymentService.getFilmsRevenueByStore(storeId);
 
		if (filmsRevenue.isEmpty()) {
			logger.warn("No revenue data found for store ID: {}", storeId);
			// return ResponseEntity.noContent().build();
			throw new PaymentNotFoundException("No revenue data found for store ID: " + storeId);
		}
 
		logger.info("Returning revenue data for {} films at store ID: {}", filmsRevenue.size(), storeId);
		return ResponseEntity.ok(filmsRevenue);
	}
 
	// Make one Payment entry
	@PutMapping("/payment/add")
	public ResponseEntity<String> addPayment(@Valid @RequestBody AddPaymentRequestDTO paymentRequest) {
		logger.info("Received request to add new payment");
 
		try {
			paymentService.createPayment(paymentRequest);
			logger.info("Payment created successfully");
			return ResponseEntity.ok("Record Created Successfully");
		} catch (PaymentNotFoundException e) {
			logger.error("Invalid payment data: {}", e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			logger.error("Error creating payment: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment request");
		}
	}
 
}