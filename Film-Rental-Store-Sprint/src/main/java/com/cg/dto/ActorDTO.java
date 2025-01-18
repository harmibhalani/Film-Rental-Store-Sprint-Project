package com.cg.dto;
 
import java.time.LocalDateTime;
import java.util.List;

import com.cg.model.Actor;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorDTO {
	
	@Positive(message = "Actor ID must be a positive number")
	private Integer actorId;
    
    @NotBlank(message = "First name is mandatory")
    @Size(max = 45, message = "First name cannot exceed 45 characters")
    
    @NotBlank(message = "First name is mandatory")
    @Size(max = 45, message = "First name cannot exceed 45 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 45, message = "Last name cannot exceed 45 characters")
    private String lastName;
    
    private LocalDateTime lastUpdate;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long filmCount;  
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    
    private List<FilmDTO> films;  
    
    //Default Constructor
    public ActorDTO() {}
    
    //Other Constructors
    public ActorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ActorDTO(Integer actorId, String firstName, String lastName, Long filmCount) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmCount = filmCount;
    }

    public ActorDTO(Integer actorId, String firstName, String lastName, LocalDateTime lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }
    
    
    //Getters and Setters
    public Integer getActorId() {
        return actorId;
    }
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public Long getFilmCount() {
        return filmCount;
    }
    public void setFilmCount(Long filmCount) {
        this.filmCount = filmCount;
    }
    public List<FilmDTO> getFilms() {
        return films;
    }
    public void setFilms(List<FilmDTO> films) {
        this.films = films;
    }
    public Actor toEntity() {
        Actor actor = new Actor();
        actor.setFirstName(this.firstName);
        actor.setLastName(this.lastName);
        return actor;
    }
}