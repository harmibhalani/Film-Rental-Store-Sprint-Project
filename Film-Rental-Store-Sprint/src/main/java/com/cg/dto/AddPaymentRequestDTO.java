package com.cg.dto;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
 
public class AddPaymentRequestDTO {
	 @NotNull(message = "Customer ID is required")
	    private Short customerId;
 
	    @NotNull(message = "Staff ID is required")
	    private Short staffId;
	//    @NotNull(message = "Rental ID is required")
	    private Integer rentalId;  // Optional for direct payments
 
	    @NotNull(message = "Amount is required")
	    @Positive(message = "Amount must be positive")
	    private BigDecimal amount;
 
	    @NotNull(message = "Payment date is required")
	    private LocalDateTime paymentDate;
 
	    // Getters and Setters
	    public Short getCustomerId() {
	        return customerId;
	    }
 
	    public void setCustomerId(@NotNull(message = "Customer ID is required") Short customerId) {
	        this.customerId = customerId;
	    }
 
	    public Short getStaffId() {
	        return staffId;
	    }
 
	    public void setStaffId(@NotNull(message = "Staff ID is required") Short staffId) {
	        this.staffId = staffId;
	    }
 
	    public Integer getRentalId() {
	        return rentalId;
	    }
 
	    public void setRentalId(Integer rentalId) {
	        this.rentalId = rentalId;
	    }
 
	    public BigDecimal getAmount() {
	        return amount;
	    }
 
	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }
 
	    public LocalDateTime getPaymentDate() {
	        return paymentDate;
	    }
 
	    public void setPaymentDate(LocalDateTime paymentDate) {
	        this.paymentDate = paymentDate;
	    }
	}
 