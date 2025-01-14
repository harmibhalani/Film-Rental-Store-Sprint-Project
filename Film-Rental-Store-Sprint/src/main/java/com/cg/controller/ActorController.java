package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.service.ActorService;
import com.cg.model.Actor;

@RestController
@RequestMapping("/api")  // Changed to /api
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors/firstname/{fn}")  // Endpoint remains the same
    public ResponseEntity<List<Actor>> findByFirstName(@PathVariable String fn) {
        List<Actor> actors = actorService.findByFirstName(fn);
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/actors/lastname/{ln}")  // Endpoint remains the same
    public ResponseEntity<List<Actor>> findByLastName(@PathVariable String ln) {
        List<Actor> actors = actorService.findByLastName(ln);
        return ResponseEntity.ok(actors);
    }
}
