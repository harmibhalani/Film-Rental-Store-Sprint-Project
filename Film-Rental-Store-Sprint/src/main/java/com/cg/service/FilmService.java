package com.cg.service;

import java.util.List;


import com.cg.model.Film;

public interface FilmService {
	
	 List<Film> getAllFilmsByTitle(String title);
}
