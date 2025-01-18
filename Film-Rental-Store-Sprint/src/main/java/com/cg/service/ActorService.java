package com.cg.service;
 
import java.util.List;
 
import com.cg.dto.ActorDTO;
import com.cg.dto.FilmActorDTO;
import com.cg.model.Actor;
import com.cg.exception.ActorNotFoundException;
import com.cg.exception.FilmNotFoundException;
import com.cg.exception.InvalidActorDataException;
import com.cg.exception.ActorAlreadyExistsException;
 
public interface ActorService {
	
    // Search Actors by Last Name
    List<Actor> getActorsByLastName(String lastName) throws ActorNotFoundException;
    // Search Actors by First Name
    List<Actor> getActorsByFirstName(String firstName) throws ActorNotFoundException;
 
    // Search Films of an Actor by Actor Id
    ActorDTO getFilmsByActorId(Integer actorId) throws ActorNotFoundException;
 
    // Find top 10 Actors by Film Count
    List<ActorDTO> getTopTenActorsByFilmCount() throws ActorNotFoundException;
 
    // Add new Actor object in DB
    ActorDTO createActor(ActorDTO actorDTO) throws ActorAlreadyExistsException, InvalidActorDataException;
 
    // Update Last Name of an Actor
    ActorDTO updateLastName(Integer actorId, String lastName) throws ActorNotFoundException;
 
    // Update First Name of an Actor
    ActorDTO updateFirstName(Integer actorId, String firstName) throws ActorNotFoundException;
 
    // Assign Film to Actor
    FilmActorDTO assignFilmToActor(Integer actorId, Integer filmId) throws ActorNotFoundException, FilmNotFoundException, InvalidActorDataException;
}