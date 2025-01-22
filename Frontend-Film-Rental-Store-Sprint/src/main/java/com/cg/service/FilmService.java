package com.cg.service;

import java.util.List;

import com.cg.model.Film;

public interface FilmService {
	
//	List<Film> getFilmsByTitle(String title) throws Exception;
	
	//Get method to search film by title
	List<Film> searchByTitle(String title) throws Exception;
	
	// PUT request to update the title of a film
	Film updateFilmTitle(Integer id, Film film);
	
	// PUT request to update the release year of a film
	Film updateByReleaseYear(Integer id, Film film);

	//Get method to search film by release year
    List<Film> searchByYear(Integer year) throws Exception;
	
	
//	 Film addFilm(Film film);
}
