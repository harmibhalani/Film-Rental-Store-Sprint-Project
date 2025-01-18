package com.cg.repositories;

import com.cg.dto.ActorDTO;
import com.cg.model.Actor;
import com.cg.model.Film;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	// Search Actors by First Name
	List<Actor> findByFirstNameIgnoreCase(String firstName);

	// Search Actors by Last Name
	List<Actor> findByLastNameIgnoreCase(String lastName);

	//	Search Films of	an Actor by	Actor Id
	@Query("SELECT f FROM Film f JOIN Film_Actor fa ON f.filmId = fa.filmId WHERE fa.actorId = :actorId")
	List<Film> findFilmsByActorId(@Param("actorId") Integer actorId);

	//	Find top 10	Actors by Film Count
	@Query("SELECT new com.cg.dto.ActorDTO(a.id, a.firstName, a.lastName, COUNT(fa.filmId)) "
			+ "FROM Actor a JOIN Film_Actor fa ON a.id = fa.actorId " + "GROUP BY a.id, a.firstName, a.lastName "
			+ "ORDER BY COUNT(fa.filmId) DESC")
	List<ActorDTO> findTopTenActorsByFilmCount();

	boolean existsByFirstNameAndLastName(String firstName, String lastName);

}