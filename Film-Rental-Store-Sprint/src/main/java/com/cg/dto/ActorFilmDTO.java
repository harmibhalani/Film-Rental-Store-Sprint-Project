package com.cg.dto;

public class ActorFilmDTO {

    private Integer actorId;
    private Integer filmId;
    private String actorFirstName;
    private String actorLastName;

    // Constructors
    public ActorFilmDTO() {}

    // Constructor for JPQL query with four fields
    public ActorFilmDTO(Integer actorId, Integer filmId, String actorFirstName, String actorLastName) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
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

    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }
}
