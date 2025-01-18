//FilmRevenueDTO.java
package com.cg.dto;
 
import java.math.BigDecimal;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
 
public class FilmRevenueDTO {
	  @NotBlank(message = "Film title is required")
    private String filmTitle;
	  @NotNull(message = "Revenue amount is required")
	  @Positive(message = "Revenue amount must be positive")
    private BigDecimal amount;
 
    public FilmRevenueDTO(String filmTitle, BigDecimal amount) {
        this.filmTitle = filmTitle;
        this.amount = amount != null ? amount : BigDecimal.ZERO;
    }
 
    // Getters
    public String getFilmTitle() {
        return filmTitle;
    }
 
    public BigDecimal getamount() {
        return amount;
    }
 
    // Setters
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }
 
    public void setTotalRevenue(BigDecimal amount) {
        this.amount = amount;
    }
}