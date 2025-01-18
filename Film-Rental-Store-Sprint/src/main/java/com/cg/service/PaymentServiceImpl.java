package com.cg.service;
 
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.cg.dto.AddPaymentRequestDTO;
import com.cg.dto.FilmRevenueDTO;
import com.cg.dto.FilmStoreRevenueDTO;
import com.cg.dto.PaymentDTO;
import com.cg.dto.StoreFilmRevenueDTO;
import com.cg.exception.PaymentNotFoundException;
import com.cg.model.Customer;
import com.cg.model.Payment;
import com.cg.model.Rental;
import com.cg.model.Staff;
import com.cg.repositories.CustomerRepository;
import com.cg.repositories.PaymentRepository;
import com.cg.repositories.RentalRepository;
import com.cg.repositories.StaffRepository;
 
@Service
public class PaymentServiceImpl implements PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
 
	@Autowired
	private PaymentRepository paymentRepository;
 
	@Autowired
	private CustomerRepository customerRepository;
 
	@Autowired
	private StaffRepository staffRepository;
 
	@Autowired
	private RentalRepository rentalRepository;
 
	@Override
	public List<PaymentDTO> getRevenueByDate() {
		try {
			List<Object[]> results = paymentRepository.findTotalRevenueByDate();
			// Debug log the results
			logger.debug("Query returned {} results", results != null ? results.size() : 0);
			if (results == null || results.isEmpty()) {
				logger.warn("No payment data found in the database");
				return List.of(); // Return empty list instead of null
			}
 
			return results.stream().map(result -> {
				try {
					// Debug log each result
					logger.debug("Processing result: date={}, amount={}", result[0], result[1]);
					Date sqlDate = (Date) result[0];
					BigDecimal amount = (result[1] != null) ? new BigDecimal(result[1].toString()) : BigDecimal.ZERO;
					return new PaymentDTO(sqlDate.toLocalDate(), amount);
 
				} catch (Exception e) {
					logger.error("Error processing result: " + e.getMessage(), e);
					throw new PaymentNotFoundException("Error processing revenue data", e);
				}
			})
					// .filter(dto -> dto != null)
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to fetch revenue by date: {}", e.getMessage(), e);
			throw new PaymentNotFoundException("Failed to fetch revenue by date", e);
		}
	}
 
	@Override
	public List<PaymentDTO> getRevenueByDateForStore(Integer storeId) {
		try {
			logger.debug("Fetching revenue data for store ID: {}", storeId);
			List<Object[]> results = paymentRepository.findTotalRevenueByDateAndStore(storeId);
 
			logger.debug("Query returned {} results for store ID: {}", results != null ? results.size() : 0, storeId);
 
			if (results == null || results.isEmpty()) {
				logger.warn("No payment data found for store ID: {}", storeId);
				return List.of();
			}
 
			return results.stream().map(result -> {
				try {
					logger.debug("Processing result for store {}: date={}, amount={}", storeId, result[0], result[1]);
					Date sqlDate = (Date) result[0];
					BigDecimal amount = (result[1] != null) ? new BigDecimal(result[1].toString()) : BigDecimal.ZERO;
					return new PaymentDTO(sqlDate.toLocalDate(), amount);
				} catch (Exception e) {
					logger.error("Error processing result for store {}: {}", storeId, e.getMessage(), e);
					// return null;
					throw new PaymentNotFoundException("Error processing store revenue data", e);
				}
			})
					// .filter(dto -> dto != null)
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to fetch revenue by date for store: {}", e.getMessage(), e);
			throw new PaymentNotFoundException("Failed to fetch revenue by date for store", e);
		}
	}
 
	@Override
	public List<FilmRevenueDTO> getRevenueByFilm() {
		try {
			logger.debug("Fetching revenue data for all films");
			List<Object[]> results = paymentRepository.findTotalRevenueByFilm();
 
			logger.debug("Query returned {} results", results != null ? results.size() : 0);
 
			if (results == null || results.isEmpty()) {
				logger.warn("No film revenue data found in the database");
				return List.of();
			}
 
			return results.stream().map(result -> {
				try {
					logger.debug("Processing result: film={}, revenue={}", result[0], result[1]);
					String filmTitle = (String) result[0];
					BigDecimal revenue = (result[1] != null) ? new BigDecimal(result[1].toString()) : BigDecimal.ZERO;
					return new FilmRevenueDTO(filmTitle, revenue);
				} catch (Exception e) {
					logger.error("Error processing film revenue result: " + e.getMessage(), e);
					// return null;
					throw new PaymentNotFoundException("Error processing film revenue data", e);
				}
			})
					// .filter(dto -> dto != null)
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to fetch revenue by film: {}", e.getMessage(), e);
			throw new PaymentNotFoundException("Failed to fetch revenue by film", e);
		}
	}
 
	@Override
	public List<FilmStoreRevenueDTO> getFilmRevenueByStore(Integer filmId) {
		try {
			logger.debug("Fetching store-wise revenue data for film ID: {}", filmId);
			List<Object[]> results = paymentRepository.findFilmRevenueByStore(filmId);
 
			logger.debug("Query returned {} results for film ID: {}", results != null ? results.size() : 0, filmId);
 
			if (results == null || results.isEmpty()) {
				logger.warn("No store revenue data found for film ID: {}", filmId);
				return List.of();
			}
 
			return results.stream().map(result -> {
				try {
					logger.debug("Processing result: store={}, film={}, revenue={}", result[0], result[1], result[2]);
					String storeAddress = (String) result[0];
					String filmTitle = (String) result[1];
					BigDecimal revenue = (result[2] != null) ? new BigDecimal(result[2].toString()) : BigDecimal.ZERO;
					return new FilmStoreRevenueDTO(storeAddress, revenue, filmTitle);
				} catch (Exception e) {
					logger.error("Error processing store revenue result for film {}: {}", filmId, e.getMessage(), e);
					// return null;
					throw new PaymentNotFoundException("Error processing store revenue data", e);
				}
			})
					// .filter(dto -> dto != null)
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to fetch revenue by store for film: {}", e.getMessage(), e);
			throw new PaymentNotFoundException("Failed to fetch revenue by store for film", e);
		}
	}
 
	@Override
	public List<StoreFilmRevenueDTO> getFilmsRevenueByStore(Integer storeId) {
		try {
			logger.debug("Fetching film-wise revenue data for store ID: {}", storeId);
			List<Object[]> results = paymentRepository.findFilmsRevenueByStore(storeId);
 
			logger.debug("Query returned {} results for store ID: {}", results != null ? results.size() : 0, storeId);
 
			if (results == null || results.isEmpty()) {
				logger.warn("No film revenue data found for store ID: {}", storeId);
				return List.of();
			}
 
			return results.stream().map(result -> {
				try {
					logger.debug("Processing result: film={}, revenue={}, store={}", result[0], result[1], result[2]);
					String filmTitle = (String) result[0];
					BigDecimal revenue = (result[1] != null) ? new BigDecimal(result[1].toString()) : BigDecimal.ZERO;
					String storeName = (String) result[2];
					return new StoreFilmRevenueDTO(filmTitle, revenue, storeName);
				} catch (Exception e) {
					logger.error("Error processing film revenue result for store {}: {}", storeId, e.getMessage(), e);
					// return null;
					throw new PaymentNotFoundException("Error processing film revenue data by store", e);
				}
			})
					// .filter(dto -> dto != null)
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("Failed to fetch films revenue by store: {}", e.getMessage(), e);
			throw new PaymentNotFoundException("Failed to fetch films revenue by store", e);
		}
	}
 
	@Override
	@Transactional
	public void createPayment(AddPaymentRequestDTO paymentRequest) {
		try {
			logger.debug("Creating new payment with amount: {}", paymentRequest.getAmount());
 
			// Validate amount
			if (paymentRequest.getAmount() == null || paymentRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
				throw new PaymentNotFoundException("Payment amount must be greater than zero.");
			}
 
			// Validate customer
			Customer customer = customerRepository.findById(paymentRequest.getCustomerId())
					.orElseThrow(() -> new PaymentNotFoundException("Invalid customer ID"));
 
			// Validate staff
			Staff staff = staffRepository.findById(paymentRequest.getStaffId())
					.orElseThrow(() -> new PaymentNotFoundException("Invalid staff ID"));
 
			// Validate rental if provided
			Rental rental = null;
			if (paymentRequest.getRentalId() != null) {
				rental = rentalRepository.findById(paymentRequest.getRentalId())
						.orElseThrow(() -> new PaymentNotFoundException("Invalid rental ID"));
 
				// Validate rental belongs to customer
				if (!rental.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
					throw new PaymentNotFoundException("Rental does not belong to the specified customer");
				}
			}
 
			// Create payment entity
			Payment payment = new Payment();
			payment.setCustomer(customer);
			payment.setStaff(staff);
			payment.setRental(rental);
			payment.setAmount(paymentRequest.getAmount());
			payment.setPaymentDate(paymentRequest.getPaymentDate());
			payment.setLastUpdate(LocalDateTime.now());
 
			// Save payment
			paymentRepository.save(payment);
			logger.info("Payment saved successfully with ID: {}", payment.getPaymentId());
 
		} catch (PaymentNotFoundException iae) {
			logger.error("Validation failed: {}", iae.getMessage(), iae);
			throw iae; // Rethrow validation exception to be handled by higher layers
		} catch (Exception e) {
			logger.error("Error saving payment: {}", e.getMessage(), e);
			throw new PaymentNotFoundException("Failed to save payment", e);
		}
	}
}
 
//    @Override
//    @Transactional
//    public void createPayment(AddPaymentRequestDTO paymentRequest) {
//        logger.debug("Creating new payment with amount: {}", paymentRequest.getAmount());
//        
//        // Validate customers
//        Customer customer = customerRepository.findById(paymentRequest.getCustomerId())
//            .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
//
//        // Validate staff
//        Staff staff = staffRepository.findById(paymentRequest.getStaffId())
//            .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));
//
//        // Validate rental if provided
//        Rental rental = null;
//        if (paymentRequest.getRentalId() != null) {
//            rental = rentalRepository.findById(paymentRequest.getRentalId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid rental ID"));
//            
//            // Validate rental belongs to customer
//            if (!rental.getCustomer().getCustomerId().equals(customer.getCustomerId())) {
//                throw new IllegalArgumentException("Rental does not belong to the specified customer");
//            }
//        }
//     // Create payment entity
//        Payment payment = new Payment();
//        payment.setCustomer(customer);
//        payment.setStaff(staff);
//        payment.setRental(rental);
//        payment.setAmount(paymentRequest.getAmount());
//        payment.setPaymentDate(paymentRequest.getPaymentDate());
//        payment.setLastUpdate(LocalDateTime.now());
//
//        // Save payment
//        try {
//            paymentRepository.save(payment);
//            logger.info("Payment saved successfully with ID: {}", payment.getPaymentId());
//            
//        } catch (Exception e) {
//            logger.error("Error saving payment: {}", e.getMessage());
//            throw new RuntimeException("Failed to save payment", e);
//        }
//    
//}
//
//    }