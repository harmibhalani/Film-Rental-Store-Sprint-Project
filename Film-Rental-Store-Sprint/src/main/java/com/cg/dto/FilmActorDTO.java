package com.cg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class FilmActorDTO {
	
	@Positive(message = "Actor ID must be a positive number")
    private Integer actorId;
	
	@Positive(message = "Film ID must be a positive number")
    private Integer filmId;
	
	@NotBlank(message = "Actor's first name is mandatory")
	@Size(max = 45, message = "Actor's first name cannot exceed 45 characters")
    private String actorFirstName;
	
	@NotBlank(message = "Actor's last name is mandatory")
	@Size(max = 45, message = "Actor's last name cannot exceed 45 characters")
    private String actorLastName;

    // Constructors
    public FilmActorDTO() {}

    public FilmActorDTO(Integer actorId, Integer filmId, String actorFirstName, String actorLastName) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
    }
    
    public FilmActorDTO(Integer actorId, Integer filmId) {
    	this.actorId=actorId;
    	this.filmId=filmId;
    	
    }

    // Getters and Setters
    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getActorFirstName() {
        return actorFirstName;
    }

    public void setActorFirstName(String actorFirstName) {
        this.actorFirstName = actorFirstName;
    }

    public String getActorLastName() {
        return actorLastName;
    }


}
