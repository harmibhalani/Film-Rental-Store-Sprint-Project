package com.cg.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_actor")
public class Film_Actor {
    
    @Id
    @Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer actorId;
    
    @Id
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;
    
    @Column(name = "last_update", nullable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id", insertable = false, updatable = false)
    private Actor actor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private Film film;
    
    // Default constructor
    public Film_Actor() {
    }
    
    // Constructor with required fields
    public Film_Actor(Integer actorId, Integer filmId) {
        this.actorId = actorId;
        this.filmId = filmId;
    }
    
    // Constructor with all fields
    public Film_Actor(Integer actorId, Integer filmId, LocalDateTime lastUpdate) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.lastUpdate = lastUpdate;
    }
    
    // Getters
    public Integer getActorId() {
        return actorId;
    }
    
    public Integer getFilmId() {
        return filmId;
    }
    
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    
    public Actor getActor() {
        return actor;
    }
    
    public Film getFilm() {
        return film;
    }
    
    // Setters
    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
    
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    public void setFilm(Film film) {
        this.film = film;
    }
    
    @PrePersist
    protected void onCreate() {
        lastUpdate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film_Actor filmActor = (Film_Actor) o;
        return Objects.equals(actorId, filmActor.actorId) && 
               Objects.equals(filmId, filmActor.filmId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }
    
    @Override
    public String toString() {
        return "FilmActor{" +
                "actorId=" + actorId +
                ", filmId=" + filmId +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}