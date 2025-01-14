package com.cg.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.PaymentDTO;
import com.cg.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentDTO> getRevenueByDate() {
        List<Object[]> results = paymentRepository.findTotalRevenueByDate();
        
        // Debug log the results
        logger.debug("Query returned {} results", results != null ? results.size() : 0);
        
        if (results == null || results.isEmpty()) {
            logger.warn("No payment data found in the database");
            return List.of(); // Return empty list instead of null
        }

        return results.stream()
            .map(result -> {
                try {
                    // Debug log each result
                    logger.debug("Processing result: date={}, amount={}", result[0], result[1]);
                    
                    Date sqlDate = (Date) result[0];
                    BigDecimal amount = (result[1] != null) ? 
                        new BigDecimal(result[1].toString()) : BigDecimal.ZERO;
                    
                    return new PaymentDTO(
                        sqlDate.toLocalDate(),
                        amount
                    );
                } catch (Exception e) {
                    logger.error("Error processing result: " + e.getMessage(), e);
                    return null;
                }
            })
            .filter(dto -> dto != null)
            .collect(Collectors.toList());
    }
}