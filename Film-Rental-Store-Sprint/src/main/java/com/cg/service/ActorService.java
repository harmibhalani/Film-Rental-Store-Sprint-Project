package com.cg.service;

import java.util.List;

import com.cg.model.Actor;

public interface ActorService {

     List<Actor> findByFirstName(String firstName);

     List<Actor> findByLastName(String lastName);
}
