package com.cg.dto;

import java.time.LocalDateTime;

public class RentalDTO {
    private Integer rentalId;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Integer inventoryId; // You can also include other fields if needed
    private Integer staffId; // Assuming you want to show staff ID

    // Constructor
    public RentalDTO(Integer rentalId, LocalDateTime rentalDate, LocalDateTime returnDate, Integer inventoryId, Integer staffId) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.inventoryId = inventoryId;
        this.staffId = staffId;
    }

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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
