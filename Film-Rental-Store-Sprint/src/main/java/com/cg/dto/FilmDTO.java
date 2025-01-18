package com.cg.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FilmDTO {
    private long filmId;
    
    @NotBlank(message = "Title is required and cannot be blank")
    @Size(max = 128, message = "Title cannot exceed 128 characters")
    private String title;
    
    @Size(max = 65535, message = "Description cannot exceed 65535 characters") 
    private String description;
    
    @Min(value = 1901, message = "Release year must be after 1900")
    @Max(value = 2155, message = "Release year cannot exceed 2155")
    private Integer releaseYear;
    
    @NotNull(message = "Language ID is required")
    private Short languageId;
    
    @NotNull(message = "Rental rate is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Rental rate must be greater than 0.00")
    @Digits(integer = 2, fraction = 2, message = "Rental rate must be a valid decimal with 2 digits before and after the decimal point")
    private Double rentalRate;
    
    @Pattern(regexp = "G|PG|PG-13|R|NC-17", message = "Rating must be one of G, PG, PG-13, R, or NC-17")
    private String rating;
    
    @NotNull(message = "Rental duration is required")
    @Min(value = 1, message = "Rental duration must be at least 1")
    @Max(value = 255, message = "Rental duration cannot exceed 255")
    private Short rentalDuration;
    
    private String languageName;
    
    private Integer length;

    // Constructors
    public FilmDTO() {}
//
//    public FilmDTO(long filmId, String title, String description, Integer releaseYear,
//                   Short languageId, Double rentalRate, String rating) {
//        this.filmId = filmId;
//        this.title = title;
//        this.description = description;
//        this.releaseYear = releaseYear;
//        this.languageId = languageId;
//        this.rentalRate = rentalRate;
//        this.rating = rating;
//    }
   
    
   public FilmDTO(Long filmId, String title, String description, Integer releaseYear,
            String rating, Integer length) {
  this.filmId = filmId;
  this.title = title;
  this.description = description;
  this.releaseYear = releaseYear;
  this.rating = rating;
  this.length = length;
}
    
    public FilmDTO(long filmId, String title, String description, Integer releaseYear, Short languageId, Double rentalRate,
		String rating, Short rentalDuration) {
	super();
	this.filmId = filmId;
	this.title = title;
	this.description = description;
	this.releaseYear = releaseYear;
	this.languageId = languageId;
	this.rentalRate = rentalRate;
	this.rating = rating;
	this.rentalDuration = rentalDuration;
}
	public FilmDTO(long filmId2, String title2, String description2, Integer releaseYear2, Short short1,
			BigDecimal rentalRate2, String rating2) {
			}

	//Getters and Setters
	
	 public long getFilmId() {
	        return filmId;
	    }
	 
	public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Short getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Short languageId) {
        this.languageId = languageId;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public Short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }
    
    public String getLanguageName() {
        return languageName;
    }
    
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }


	public void setActorId(int i) {
		// TODO Auto-generated method stub
		
	}
    
    
}
