package com.cg.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "film_actor")
public class Film_Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED")
	private Integer actorId;

	@Id
	@Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
	private Integer filmId;

	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime lastUpdate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_id", insertable = false, updatable = false)
	private Actor actor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "film_id", insertable = false, updatable = false)
	private Film film;

	// Default constructor
	public Film_Actor() {
		this.lastUpdate = LocalDateTime.now();
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

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	// Override hashCode
	@Override
	public int hashCode() {
		return Objects.hash(actor, actorId, film, filmId, lastUpdate);
	}
	
	// Override equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film_Actor other = (Film_Actor) obj;
		return Objects.equals(actor, other.actor) && Objects.equals(actorId, other.actorId)
				&& Objects.equals(film, other.film) && Objects.equals(filmId, other.filmId)
				&& Objects.equals(lastUpdate, other.lastUpdate);
	}

}