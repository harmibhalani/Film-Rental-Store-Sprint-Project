package com.cg;
 
import com.cg.controller.PaymentController;
import com.cg.dto.*;
import com.cg.exception.PaymentNotFoundException;
import com.cg.service.PaymentService;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import org.springframework.http.ResponseEntity;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class PaymentTest {
 
	@Mock
	private PaymentService paymentService;
 
	@InjectMocks
	private PaymentController paymentController;
 
	@Test
	void testGetPaymentRevenueByDate() {
		List<PaymentDTO> mockResponse = List.of(new PaymentDTO(LocalDate.now(), BigDecimal.valueOf(100)));
		when(paymentService.getRevenueByDate()).thenReturn(mockResponse);
 
		ResponseEntity<?> response = paymentController.getPaymentRevenueByDate();
 
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertTrue(((List<?>) response.getBody()).size() > 0);
	}
 
	@Test
	void testGetPaymentRevenueByDate_NoContent() {
		when(paymentService.getRevenueByDate()).thenReturn(Collections.emptyList());
 
		ResponseEntity<?> response = paymentController.getPaymentRevenueByDate();
 
		assertEquals(204, response.getStatusCodeValue());
		assertEquals("No payment data found for the given date range.", response.getBody());
	}
 
	@Test
	void testGetStoreRevenueByDate() {
		List<PaymentDTO> mockResponse = List.of(new PaymentDTO(LocalDate.now(), BigDecimal.valueOf(200)));
		when(paymentService.getRevenueByDateForStore(1)).thenReturn(mockResponse);
 
		ResponseEntity<List<PaymentDTO>> response = paymentController.getStoreRevenueByDate(1);
 
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(1, response.getBody().size());
	}
 
	@Test
	void testGetStoreRevenueByDate_NotFound() {
		when(paymentService.getRevenueByDateForStore(2)).thenReturn(Collections.emptyList());
 
		PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class,
				() -> paymentController.getStoreRevenueByDate(2));
 
		assertEquals("No payment data found for store ID: 2", exception.getMessage());
	}
 
	@Test
	void testGetFilmwiseRevenue() {
		List<FilmRevenueDTO> mockResponse = List.of(new FilmRevenueDTO("Film1", BigDecimal.valueOf(300)));
		when(paymentService.getRevenueByFilm()).thenReturn(mockResponse);
 
		ResponseEntity<List<FilmRevenueDTO>> response = paymentController.getFilmwiseRevenue();
 
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(1, response.getBody().size());
	}
 
	@Test
	void testGetFilmwiseRevenue_NotFound() {
		when(paymentService.getRevenueByFilm()).thenReturn(Collections.emptyList());
 
		PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class,
				() -> paymentController.getFilmwiseRevenue());
 
		assertEquals("No film revenue data found.", exception.getMessage());
	}
 
	@Test
	void testGetFilmRevenueByStore_NotFound() {
		when(paymentService.getFilmRevenueByStore(2)).thenReturn(Collections.emptyList());
 
		PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class,
				() -> paymentController.getFilmRevenueByStore(2));
 
		assertEquals("No revenue data found for film ID: 2", exception.getMessage());
	}
 
	@Test
	void testGetFilmsRevenueByStore_NotFound() {
		when(paymentService.getFilmsRevenueByStore(2)).thenReturn(Collections.emptyList());
 
		PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class,
				() -> paymentController.getFilmsRevenueByStore(2));
 
		assertEquals("No revenue data found for store ID: 2", exception.getMessage());
	}
 
	@Test
	void testAddPayment() {
		AddPaymentRequestDTO request = new AddPaymentRequestDTO();
 
		doNothing().when(paymentService).createPayment(request);
 
		ResponseEntity<String> response = paymentController.addPayment(request);
 
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Record Created Successfully", response.getBody());
	}
 
	@Test
	void testAddPayment_Error() {
		AddPaymentRequestDTO request = new AddPaymentRequestDTO();
 
		doThrow(new PaymentNotFoundException("Invalid payment data")).when(paymentService).createPayment(request);
 
		ResponseEntity<String> response = paymentController.addPayment(request);
 
		assertEquals(400, response.getStatusCodeValue());
		assertEquals("Invalid payment data", response.getBody());
	}
}