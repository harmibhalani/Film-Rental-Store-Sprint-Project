package com.cg.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.model.Actor;
import com.cg.service.ActorService;
 
@Controller
@RequestMapping("/homePage/dashboard/actorManagement")
public class ActorController {
 
    @Autowired
    private ActorService actorService;
 
    // GET request to search actors by first name or last name
    @GetMapping("/search-actor")
    public ResponseEntity<?> searchActors(
        @RequestParam String searchType,
        @RequestParam String searchTerm) {
        try {
            List<Actor> results;
            switch (searchType) {
                case "firstname":
                    results = actorService.searchByFirstName(searchTerm);
                    break;
                case "lastname":
                    results = actorService.searchByLastName(searchTerm);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid search type");
            }
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error searching actors: " + e.getMessage());
        }
    }
 
    // PUT request to update the first name of an actor
    @PutMapping("/update/firstname/{fn}")
    public ResponseEntity<?> updateActorFirstName(
        @PathVariable("fn") Integer actorId,
        @RequestBody Actor actorDTO) {
        try {
            Actor updatedActor = actorService.updateActorFirstName(actorId, actorDTO);
            return ResponseEntity.ok(updatedActor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error updating actor first name: " + e.getMessage());
        }
    }
 
    // PUT request to update the last name of an actor
    @PutMapping("/update/lastname/{id}")
    public ResponseEntity<?> updateActorLastName(
        @PathVariable("id") Integer actorId,
        @RequestBody Actor actorDTO) {
        try {
            Actor updatedActor = actorService.updateActorLastName(actorId, actorDTO);
            return ResponseEntity.ok(updatedActor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error updating actor last name: " + e.getMessage());
        }
    }
    
    // POST mapping to create an actor
    @PostMapping("/post")
	public ResponseEntity<?> createActor( @RequestBody Actor actorDTO) {
		try {
			Actor createdActor = actorService.createActor(actorDTO);
			return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating actor: " + e.getMessage());
		}
	}
}