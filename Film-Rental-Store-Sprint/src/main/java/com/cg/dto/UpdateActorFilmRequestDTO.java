package com.cg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UpdateActorFilmRequestDTO {

	@NotBlank(message = "Last name is mandatory")
	@Size(max = 45, message = "Last name cannot exceed 45 characters")
	private String lastName;

	@NotBlank(message = "First name is mandatory")
	@Size(max = 45, message = "First name cannot exceed 45 characters")
	private String firstName;
	
	@Positive(message = "Film ID must be a positive number")
	private Long filmId;

	public UpdateActorFilmRequestDTO(String updateFirstName, String updateLastName) {
		this.firstName = updateFirstName;
		this.lastName = updateLastName;
	}
	
	//Default Constructors
	public UpdateActorFilmRequestDTO() {}
	
	//Getters and Setters
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getFilmId() {
		return filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}
}
