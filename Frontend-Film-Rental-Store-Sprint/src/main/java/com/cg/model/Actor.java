package com.cg.model;

import java.time.LocalDateTime;
import java.util.List;

public class Actor {

	private Integer actorId;
	private String firstName;
	private String lastName;
	private LocalDateTime lastUpdate;
	private List<Film> filmActors; 
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
    public List<Film> getFilms() {
        return filmActors;
    }
 
    public void setFilms(List<Film> filmActors) {
        this.filmActors = filmActors;
    }

	
	public Actor(Integer actorId, String firstName, String lastName) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Actor() {
		super();
	}


}
