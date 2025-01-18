package com.cg.dto;

import java.math.BigDecimal;
import java.util.List;

import com.cg.model.Language;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;


public class FilmDTO {
	private long filmId;

	private String title;

	private String description;

	private Integer releaseYear;
	
	private Language languageId;
	
	private Short originalLanguageId;
	
	private BigDecimal rentalRate;

	private String rating;

	private Short rentalDuration;

	private Integer length;
	
	private String specialFeatures;
	
	 private Double replacementCost;

	private List<ActorDTO> actors;

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

	public Language getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Language languageId) {
		this.languageId = languageId;
	}

	public Short getOriginalLanguageId() {
		return originalLanguageId;
	}

	public void setOriginalLanguageId(Short originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

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

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public List<ActorDTO> getActors() {
		return actors;
	}

	public void setActors(List<ActorDTO> actors) {
		this.actors = actors;
	}

	public FilmDTO(long filmId, String title, String description, Integer releaseYear, Language languageId,
			Short originalLanguageId, BigDecimal rentalRate, String rating, Short rentalDuration, 
			Integer length, String specialFeatures, Double replacementCost) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.originalLanguageId = originalLanguageId;
		this.rentalRate = rentalRate;
		this.rating = rating;
		this.rentalDuration = rentalDuration;
		this.length = length;
		this.specialFeatures = specialFeatures;
		this.replacementCost = replacementCost;
	}

	public FilmDTO(long filmId, String title, String description, Integer releaseYear, Language languageId,
			Short originalLanguageId, BigDecimal rentalRate, String rating, Short rentalDuration, Integer length,
			String specialFeatures, Double replacementCost, List<ActorDTO> actors) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.originalLanguageId = originalLanguageId;
		this.rentalRate = rentalRate;
		this.rating = rating;
		this.rentalDuration = rentalDuration;
		this.length = length;
		this.specialFeatures = specialFeatures;
		this.replacementCost = replacementCost;
		this.actors = actors;
	}

	public FilmDTO() {
		super();
	}

	


}
