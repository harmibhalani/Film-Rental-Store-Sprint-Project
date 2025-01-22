package com.cg.model;
 
import java.time.LocalDateTime;
 
public class Rental {
    private Integer rentalId;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Integer inventoryId;
    private Short staffId;
    private Short customerId;
 
    // Getters and Setters
    public Integer getRentalId() {
        return rentalId;
    }
 
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }
 
    public LocalDateTime getRentalDate() {
        return rentalDate;
    }
 
    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }
 
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
 
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
 
    public Integer getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
 
    public Short getStaffId() {
        return staffId;
    }
 
    public void setStaffId(Short staffId) {
        this.staffId = staffId;
    }
 
    public Short getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }
}
 
