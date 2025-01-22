package com.cg.service;
 
import java.util.List;
 
import com.cg.model.Actor;
 
public interface ActorService {
    List<Actor> searchByFirstName(String firstName) throws Exception;
 
    List<Actor> searchByLastName(String lastName) throws Exception;
 
    Actor updateActorFirstName(Integer actorId, Actor actor);
 
    Actor updateActorLastName(Integer actorId, Actor actor);

	Actor createActor(Actor actorDTO);

	List<Actor> getAllActors();
}