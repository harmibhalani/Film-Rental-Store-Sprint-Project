package com.cg.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
 
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public class PaymentDTO {
	 @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;
	 @NotNull(message = "Amount is required")
	 @Positive(message = "Amount must be positive")
    private BigDecimal amount ;
    // No-argument constructor
    public PaymentDTO() {}
    // Constructor must exactly match the types from the query
    public PaymentDTO(LocalDate paymentDate, BigDecimal amount) {
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
 
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
 
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
 
	public BigDecimal getAmount() {
		return amount;
	}
 
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}