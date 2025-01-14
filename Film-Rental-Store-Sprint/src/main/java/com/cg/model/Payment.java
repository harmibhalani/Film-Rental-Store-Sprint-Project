package com.cg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payment", indexes = {
    @Index(name = "idx_fk_staff_id", columnList = "staff_id"),
    @Index(name = "idx_fk_customer_id", columnList = "customer_id")
})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer paymentId;

    @NotNull(message = "Customer is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", 
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_payment_customer"))
    private Customer customer;

    @NotNull(message = "Staff member is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", 
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_payment_staff"))
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", 
                foreignKey = @ForeignKey(name = "fk_payment_rental"))
    private Rental rental;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Column(name = "amount", nullable = false, precision = 5, scale = 2)
    private BigDecimal amount;

    @NotNull(message = "Payment date is required")
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "last_update", 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    // Default constructor
    public Payment() {
        this.lastUpdate = LocalDateTime.now();
    }

    // All-args constructor
    public Payment(Integer paymentId, Customer customer, Staff staff, Rental rental,
                  BigDecimal amount, LocalDateTime paymentDate, LocalDateTime lastUpdate) {
        this.paymentId = paymentId;
        this.customer = customer;
        this.staff = staff;
        this.rental = rental;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate != null ? lastUpdate : LocalDateTime.now();
    }

    // Getters
    public Integer getPaymentId() {
        return paymentId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public Rental getRental() {
        return rental;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    // Setters
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentId, payment.paymentId) &&
               Objects.equals(customer, payment.customer) &&
               Objects.equals(staff, payment.staff) &&
               Objects.equals(rental, payment.rental) &&
               Objects.equals(amount, payment.amount) &&
               Objects.equals(paymentDate, payment.paymentDate) &&
               Objects.equals(lastUpdate, payment.lastUpdate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(paymentId, customer, staff, rental, 
                          amount, paymentDate, lastUpdate);
    }

    // toString method
    @Override
    public String toString() {
        return "Payment{" +
               "paymentId=" + paymentId +
               ", customer=" + customer +
               ", staff=" + staff +
               ", rental=" + rental +
               ", amount=" + amount +
               ", paymentDate=" + paymentDate +
               ", lastUpdate=" + lastUpdate +
               '}';
    }
}