package com.cg.dto;
 
import java.math.BigDecimal;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
 
public class StoreFilmRevenueDTO {
	 @NotBlank(message = "Film title is required")
    private String filmTitle;
	  @NotNull(message = "Revenue amount is required")
	    @Positive(message = "Revenue amount must be positive")
    private BigDecimal amount;
    //private String storeName;  // Store identifier for reference
 
    public StoreFilmRevenueDTO(String filmTitle, BigDecimal amount, String storeName) {
        this.filmTitle = filmTitle;
        this.amount = amount != null ? amount : BigDecimal.ZERO;
       // this.storeName = storeName;
    }
 
    // Getters
    public String getFilmTitle() {
        return filmTitle;
    }
 
    public BigDecimal getamount() {
        return amount;
    }
 
//    public String getStoreName() {
//        return storeName;
//    }
 
    // Setters
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }
 
    public void setamount(BigDecimal amount) {
        this.amount = amount;
    }
 
//    public void setStoreName(String storeName) {
//        this.storeName = storeName;
//    }
}