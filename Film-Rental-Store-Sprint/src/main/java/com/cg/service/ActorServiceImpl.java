package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Actor;
import com.cg.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
        
    }

    
    @Override
    public List<Actor> findByFirstName(String firstName) {
        return actorRepository.findByFirstNameContainingIgnoreCase(firstName);
    }
    
    @Override
    public List<Actor> findByLastName(String lastName) {
    	return actorRepository.findByLastNameContainingIgnoreCase(lastName);
    }
}
