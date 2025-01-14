package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Film;
import com.cg.repositories.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService{
	
	 @Autowired
	    private FilmRepository filmRepository;

	 public List<Film> getAllFilmsByTitle(String title) {
	        return filmRepository.findByTitle(title);
	    }
}
