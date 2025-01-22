package com.cg.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ActorDTO;
import com.cg.dto.ActorDataDTO;
import com.cg.dto.FilmActorDTO;
import com.cg.dto.FilmDTO;
import com.cg.dto.FilmDataDTO;
import com.cg.dto.UpdateActorFilmRequestDTO;
import com.cg.exception.ActorAlreadyExistsException;
import com.cg.service.ActorService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api")
@Validated
public class ActorController {
 
	@Autowired
	private ActorService actorService;
 
	// Search Actors by Last Name
	@GetMapping("/actors/lastname/{ln}")
	public ResponseEntity<?> getActorsByLastName(@PathVariable("ln") String lastName) {
	    if (lastName == null || lastName.trim().isEmpty()) {
	        return ResponseEntity.badRequest().body("Last name must not be empty.");
	    }
	    
	    List<ActorDataDTO> actors = actorService.getActorsByLastName(lastName);
	    if (actors.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("No actors found with the last name: " + lastName);
	    }
	    return ResponseEntity.ok(actors);
	}

 
    @GetMapping("/actors/firstname/{fn}")
    public ResponseEntity<?> getActorsByFirstName(@PathVariable("fn") String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("First name must not be empty.");
        }
        List<ActorDataDTO> actors = actorService.getActorsByFirstName(firstName);
        if (actors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No actors found with the first name: " + firstName);
        }
        return ResponseEntity.ok(actors);
    }
 
	// Search Films of an Actor by Actor Id
    @GetMapping("/actors/{id}/films")
    public ResponseEntity<?> getFilmsByActorId(@PathVariable Integer id) {
        ActorDTO films = actorService.getFilmsByActorId(id);
        return ResponseEntity.ok(films);
    }
 
	// Find top 10 Actors by Film Count
	@GetMapping("/actors/toptenbyfilmcount")
	public ResponseEntity<List<ActorDTO>> getTopTenActorsByFilmCount() {
		List<ActorDTO> topActors = actorService.getTopTenActorsByFilmCount();
 
		return ResponseEntity.ok(topActors);
	}
 
	// Add new Actor object in DB
	@PostMapping("/actors/post")
	public ResponseEntity<?> createActor(@Valid @RequestBody ActorDTO actorDTO) {
		try {
			ActorDTO createdActor = actorService.createActor(actorDTO);
			return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
		} catch (ActorAlreadyExistsException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
 
	// Update Last Name of an Actor
	@PutMapping("/actors/update/lastname/{id}")
	public ResponseEntity<ActorDTO> updateLastName(@Valid @PathVariable Integer id, @RequestBody UpdateActorFilmRequestDTO request) {
		ActorDTO updatedActor = actorService.updateLastName(id, request.getLastName());
 
		return ResponseEntity.ok(updatedActor);
	}
 
	// Update First Name of an Actor
	@PutMapping("/actors/update/firstname/{id}")
	public ResponseEntity<ActorDTO> updateFirstName(@Valid @PathVariable Integer id, @RequestBody UpdateActorFilmRequestDTO request) {
		ActorDTO updatedActor = actorService.updateFirstName(id, request.getFirstName());
 
		return ResponseEntity.ok(updatedActor);
	}
 
	// Assign Film to Actor
	@PutMapping("/actors/{actorId}/film")
	public ResponseEntity<?> assignFilmToActor(
			@PathVariable Integer actorId,
			 @RequestBody FilmActorDTO filmActorDTO) {
 
		FilmActorDTO assignedFilm = actorService.assignFilmToActor(actorId, filmActorDTO.getFilmId());
 
		return ResponseEntity.ok(assignedFilm);
	}
	
	//Get all actors
	@GetMapping("/actors/allactors")
	public ResponseEntity<?> getAllActors() {
	    List<ActorDataDTO> actors = actorService.getAllActors();
	    if (actors.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("No actors found.");
	    }
	    return ResponseEntity.ok(actors);
	}
}
