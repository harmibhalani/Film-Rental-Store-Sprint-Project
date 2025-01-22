package com.cg.model;
 
import java.math.BigDecimal;
import java.time.LocalDate;
 
public class Payment {
    private LocalDate paymentDate;
    private BigDecimal amount;
    public Payment() {
        super();
    }
public Payment(LocalDate paymentDate, BigDecimal amount, String filmTitle, String storeAddress) {
		super();
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