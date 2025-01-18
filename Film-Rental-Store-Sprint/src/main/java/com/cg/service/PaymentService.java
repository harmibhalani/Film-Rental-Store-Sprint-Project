package com.cg.service;
 
import java.util.List;
 
import com.cg.dto.AddPaymentRequestDTO;
import com.cg.dto.FilmRevenueDTO;
import com.cg.dto.FilmStoreRevenueDTO;
import com.cg.dto.PaymentDTO;
import com.cg.dto.StoreFilmRevenueDTO;
import com.cg.exception.PaymentNotFoundException;
 
public interface PaymentService {
	List<PaymentDTO> getRevenueByDate() throws PaymentNotFoundException;
 
	List<PaymentDTO> getRevenueByDateForStore(Integer storeId) throws PaymentNotFoundException;
 
	List<FilmRevenueDTO> getRevenueByFilm() throws PaymentNotFoundException;
 
	List<FilmStoreRevenueDTO> getFilmRevenueByStore(Integer filmId) throws PaymentNotFoundException;
 
	List<StoreFilmRevenueDTO> getFilmsRevenueByStore(Integer storeId) throws PaymentNotFoundException;
 
	void createPayment(AddPaymentRequestDTO paymentRequest) throws PaymentNotFoundException;
}
 