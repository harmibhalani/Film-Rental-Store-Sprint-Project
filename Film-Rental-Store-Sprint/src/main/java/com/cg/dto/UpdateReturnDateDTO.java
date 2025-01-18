package com.cg.dto;
 
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
public class UpdateReturnDateDTO {
	  @NotNull(message = "Return date cannot be null.")
	    @FutureOrPresent(message = "Return date must be in the present or future.")
    private String returnDate; // Expecting a string representation of LocalDateTime
 
    public String getReturnDate() {
        return returnDate;
    }
 
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}