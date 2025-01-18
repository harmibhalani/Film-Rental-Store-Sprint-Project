package com.cg.dto;
 
import java.math.BigDecimal;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
 
public class FilmStoreRevenueDTO {
	@NotBlank(message = "Store address is required")
	private String storeAddress;
 
	@NotNull(message = "Revenue amount is required")
	@Positive(message = "Revenue amount must be positive")
	private BigDecimal amount;
	// private String filmTitle; // Added to include film information in response
 
	public FilmStoreRevenueDTO(String storeAddress, BigDecimal amount, String filmTitle) {
		this.storeAddress = storeAddress;
		this.amount = amount != null ? amount : BigDecimal.ZERO;
		// this.filmTitle = filmTitle;
	}
 
	// Getters
	public String getStoreAddress() {
		return storeAddress;
	}
 
	public BigDecimal getamount() {
		return amount;
	}
 
//    public String getFilmTitle() {
//        return filmTitle;
//    }
 
	// Setters
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
 
	public void setamount(BigDecimal amount) {
		this.amount = amount;
	}
 
//    public void setFilmTitle(String filmTitle) {
//        this.filmTitle = filmTitle;
//    }
}