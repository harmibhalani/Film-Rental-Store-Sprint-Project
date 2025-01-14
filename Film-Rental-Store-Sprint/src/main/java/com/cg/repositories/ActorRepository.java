package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

     List<Actor> findByFirstNameContainingIgnoreCase(String firstName);

     List<Actor> findByLastNameContainingIgnoreCase(String lastName);
}
