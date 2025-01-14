package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Film;
import com.cg.service.FilmService;

@RestController
@RequestMapping("/api") 
public class FilmController {	
	
	@Autowired
    private FilmService filmService;
	
	 @GetMapping("/films/title/{title}")
	    public ResponseEntity<List<Film>> getFilmsByTitlePath(@PathVariable String title) {
	        List<Film> films = filmService.getAllFilmsByTitle(title);
	        return ResponseEntity.ok(films);
	    }

}
