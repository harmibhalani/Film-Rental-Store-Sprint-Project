package com.cg.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ActorDTO;
import com.cg.dto.ActorDataDTO;
import com.cg.dto.FilmActorDTO;
import com.cg.dto.FilmDTO;
import com.cg.dto.FilmDataDTO;
import com.cg.exception.ActorAlreadyExistsException;
import com.cg.exception.ActorNotFoundException;
import com.cg.exception.FilmAssignmentException;
import com.cg.exception.FilmNotFoundException;
import com.cg.exception.InvalidActorDataException;
import com.cg.model.Actor;
import com.cg.model.Film;
import com.cg.repositories.ActorRepository;
import com.cg.repositories.FilmRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;

	@PersistenceContext
	private EntityManager entityManager;

	// Search Actors by Last Name
	// Search Actors by Last Name
	@Override
	public List<ActorDataDTO> getActorsByLastName(String lastName) {
		List<Actor> actors = actorRepository.findByLastNameIgnoreCase(lastName);
		if (actors.isEmpty()) {
			throw new ActorNotFoundException("No actors found with the last name: " + lastName);
		}

		// Convert Actor entities to ActorDataDTO
		return actors.stream().map(actor -> new ActorDataDTO(actor.getId(), actor.getFirstName(), actor.getLastName(),
				actor.getLastUpdate())).collect(Collectors.toList());
	}

	// Search Actors by First Name
	@Override
	public List<ActorDataDTO> getActorsByFirstName(String firstName) {
		List<Actor> actors = actorRepository.findByFirstNameIgnoreCase(firstName);
		if (actors.isEmpty()) {
			throw new ActorNotFoundException("No actors found with the first name: " + firstName);
		}

		// Convert Actor entities to ActorDataDTO
		return actors.stream().map(actor -> new ActorDataDTO(actor.getId(), actor.getFirstName(), actor.getLastName(),
				actor.getLastUpdate())).collect(Collectors.toList());
	}

	// Search Films of an Actor by Actor Id
	@Override
    public ActorDTO getFilmsByActorId(Integer actorId) {
    	
    	Actor actor = actorRepository.findById(actorId).orElseThrow();
        List<Film> films = actorRepository.findFilmsByActorId(actorId);
//        return films.stream()
//                .map(this::convertToFilmDTO)
//                .collect(Collectors.toList());
//    }
        ActorDTO actorDTO = new ActorDTO( actor.getId(),actor.getFirstName(),actor.getLastName(),actor.getLastUpdate());
        
        List<FilmDTO> filmDTO = films.stream().map(
        		film -> new FilmDTO(film.getFilmId(),
        				film.getTitle(),
        				
        				film.getDescription(),
        				film.getReleaseYear(),
        				film.getLanguage(),
        				film.getOriginalLanguageId(),
        				film.getRentalRate(),
        				film.getRating(),
        				film.getRentalDuration(),
        				film.getLength(),
        				film.getSpecialFeatures(),
        				film.getReplacementCost()))
        		.collect(Collectors.toList());
        
        actorDTO.setFilms(filmDTO);
        return actorDTO;
	}
        		
//    // Converts Film entity to FilmDTO
//	private FilmDTO convertToFilmDTO(Film film) {
//		return new FilmDTO(film.getFilmId(), film.getTitle(), film.getDescription(), film.getReleaseYear(),
//				film.getLanguage(), film.getOriginalLanguageId(), film.getRentalRate(), film.getRating(),
//				film.getRentalDuration(), film.getLength(), film.getSpecialFeatures(), film.getReplacementCost());
//	}

	// Find top 10 Actors by Film Count
	@Override
	public List<ActorDTO> getTopTenActorsByFilmCount() {
		return actorRepository.findTopTenActorsByFilmCount().stream().limit(10).collect(Collectors.toList());
	}

	// Add new Actor object in DB
	@Override
	public ActorDTO createActor(ActorDTO actorDTO) {
		if (actorRepository.existsByFirstNameAndLastName(actorDTO.getFirstName(), actorDTO.getLastName())) {
			throw new ActorAlreadyExistsException("Actor already exists with the same name.");
		}
		// Validate other data if necessary
		if (actorDTO.getFirstName() == null || actorDTO.getLastName() == null) {
			throw new InvalidActorDataException("First name and last name cannot be null.");
		}

		Actor actor = actorDTO.toEntity();
		actor.setLastUpdate(LocalDateTime.now());
		Actor savedActor = actorRepository.save(actor);
		return new ActorDTO(savedActor.getId(), savedActor.getFirstName(), savedActor.getLastName(),
				savedActor.getLastUpdate());
	}

	// Update Last Name of an Actor
	@Override
	public ActorDTO updateLastName(Integer actorId, String lastName) {
		Actor actor = actorRepository.findById(actorId)
				.orElseThrow(() -> new ActorNotFoundException("Actor not found with ID: " + actorId));
		actor.setLastName(lastName);
		Actor updatedActor = actorRepository.save(actor);
		return new ActorDTO(updatedActor.getId(), updatedActor.getFirstName(), updatedActor.getLastName(),
				updatedActor.getLastUpdate());
	}

	// Update First Name of an Actor
	@Override
	public ActorDTO updateFirstName(Integer actorId, String firstName) {
		Actor actor = actorRepository.findById(actorId)
				.orElseThrow(() -> new ActorNotFoundException("Actor not found with ID: " + actorId));
		actor.setFirstName(firstName);
		Actor updatedActor = actorRepository.save(actor);
		return new ActorDTO(updatedActor.getId(), updatedActor.getFirstName(), updatedActor.getLastName(),
				updatedActor.getLastUpdate());
	}

	// Assign Film to Actor
	@Override
	@Transactional
	public FilmActorDTO assignFilmToActor(Integer actorId, Integer filmId) {
		try {
			String checkQuery = "SELECT COUNT(*) FROM film_actor WHERE actor_id = :actorId AND film_id = :filmId";
			Query query = entityManager.createNativeQuery(checkQuery);
			query.setParameter("actorId", actorId);
			query.setParameter("filmId", filmId);

			Long count = ((Number) query.getSingleResult()).longValue();
			if (count > 0) {
				throw new FilmAssignmentException("Film is already assigned to this actor.");
			}

			Actor actor = actorRepository.findById(actorId)
					.orElseThrow(() -> new ActorNotFoundException("Actor not found with ID: " + actorId));

			Film film = filmRepository.findById(filmId)
					.orElseThrow(() -> new FilmNotFoundException("Film not found with ID: " + filmId));

			String insertQuery = "INSERT INTO film_actor (actor_id, film_id, last_update) VALUES (:actorId, :filmId, :lastUpdate)";
			Query insertNativeQuery = entityManager.createNativeQuery(insertQuery);
			insertNativeQuery.setParameter("actorId", actorId);
			insertNativeQuery.setParameter("filmId", filmId);
			insertNativeQuery.setParameter("lastUpdate", LocalDateTime.now());
			insertNativeQuery.executeUpdate();

			entityManager.flush();
			entityManager.clear();

			return new FilmActorDTO(actorId, filmId, actor.getFirstName(), actor.getLastName());
		} catch (Exception e) {
			throw new FilmAssignmentException("Error assigning film to actor: " + e.getMessage());
		}
	}
	
	//Get all actors
	@Override
    public List<ActorDataDTO> getAllActors() {
        // Fetch all actors from the repository and convert them to DTOs in one step
        return actorRepository.findAll().stream()
                .map(actor -> new ActorDataDTO(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate()))
                .collect(Collectors.toList());
    }
}