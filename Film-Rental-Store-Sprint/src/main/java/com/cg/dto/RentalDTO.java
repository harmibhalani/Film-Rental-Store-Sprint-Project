package com.cg.dto;
 
import java.time.LocalDateTime;
 
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
 
public class RentalDTO {
	@NotNull(message = "Rental ID cannot be null.")
    @Min(value = 1, message = "Rental ID must be a positive number.")
    private Integer rentalId;

 
    @NotNull(message = "Rental date cannot be null.")
    @PastOrPresent(message = "Rental date must be in the past or present.")
	private LocalDateTime rentalDate;
 
    @NotNull(message = "Return date cannot be null.")
    @FutureOrPresent(message = "Return date must be in the present or future.")
    private LocalDateTime returnDate;
 
    @NotNull(message = "Inventory ID cannot be null.")
    @Min(value = 1, message = "Inventory ID must be a positive number.")
    private Integer inventoryId; // You can also include other fields if needed
 
    @NotNull(message = "Staff ID cannot be null.")
    @Min(value = 1, message = "Staff ID must be a positive number.")
    private Short staffId; // Assuming you want to show staff ID
 
    @NotNull(message = "Customer ID cannot be null.")
    @Min(value = 1, message = "Customer ID must be a positive number.")
    private Short customerId;
 
    
    public RentalDTO(Integer rentalId, LocalDateTime rentalDate, LocalDateTime returnDate, Integer inventoryId, Short staffId , Short customerId) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.inventoryId = inventoryId;
        this.staffId = staffId;
        this.customerId = customerId;
    }
 

//    public RentalDTO(Integer rentalId2, LocalDateTime rentalDate2, LocalDateTime returnDate2, Integer inventoryId2,
//			Integer staffId2, Integer customerId2) {
//		// TODO Auto-generated constructor stub
//	}





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
