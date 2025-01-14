package com.cg.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDTO {
    private LocalDate date;
    private BigDecimal totalRevenue;

    // No-argument constructor
    public PaymentDTO() {}

    // Constructor must exactly match the types from the query
    public PaymentDTO(LocalDate date, BigDecimal totalRevenue) {
        this.date = date;
        this.totalRevenue = totalRevenue;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}