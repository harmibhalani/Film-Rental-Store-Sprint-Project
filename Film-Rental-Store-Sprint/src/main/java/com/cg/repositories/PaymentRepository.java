package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.dto.PaymentDTO;
import com.cg.model.Payment;
import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Changed to return specific columns with aliases
    @Query(value = "SELECT DATE(payment_date) as payment_date, " +
                   "COALESCE(SUM(amount), 0.00) as total_amount " +
                   "FROM payment " +
                   "GROUP BY DATE(payment_date) " +
                   "ORDER BY payment_date", 
           nativeQuery = true)
    List<Object[]> findTotalRevenueByDate();
}