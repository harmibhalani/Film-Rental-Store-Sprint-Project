package com.cg.model;

import java.math.BigDecimal;

public class Film{

    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
//    private Language languageId;
    private BigDecimal rentalRate;
    private String rating;
    private Short rentalDuration;
    private Integer length;
//    private Integer languageId;

    // Getters and setters for all fields
    
    

	public Integer getFilmId() {
        return filmId;
    }

    public Film() {
		super();
	}




	public Film(Integer filmId, String title, String description, Integer releaseYear, BigDecimal rentalRate,
			String rating, Short rentalDuration, Integer length) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rentalRate = rentalRate;
		this.rating = rating;
		this.rentalDuration = rentalDuration;
		this.length = length;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setFilmId(Integer filmId) {
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

//    public Language getLanguageId() {
//        return languageId;
//    }
//
//    public void setLanguageId(Language languageId) {
//        this.languageId = languageId;
//    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
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


}
