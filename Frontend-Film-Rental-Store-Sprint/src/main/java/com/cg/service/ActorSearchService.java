package com.cg.service;
 
import java.util.List;
import com.cg.model.Actor;
 
public interface ActorSearchService {
 
	public List<Actor> searchByLastName(String lastName);
	public List<Actor> searchByFirstName(String firstName);
	public Actor searchFilmsByActorId(Integer actorId);
}