package com.cg.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.dto.FilmActorDTO;
import com.cg.dto.FilmCategoryDTO;
import com.cg.dto.FilmDTO;
import com.cg.model.Actor;
import com.cg.model.Category;
import com.cg.model.Film;
import com.cg.model.Film_Actor;
import com.cg.model.Language;

public interface FilmService {
	
	 //Add new Film object in DB
	 Film addFilm(Film film);
	 
	//Search Films by Title
	 List<Film> getFilmsByTitle(String title);
	 
	//Search Films by Release Year
	 List<Film> getFilmsByYear(Integer year);
	 
	// Search Films where Rental Duration is greater than {rd}
	 List<Film> getFilmsByRentalDurationGreaterThan(Integer duration);
	 
	//Search Films where Rental Rate is greater than {rate}
	 List<Film> getFilmsByRentalRateGreaterThan(Double rate);
	 
	//Search Films where Length is greater than {length}
	 List<Film> getFilmsByLengthGreaterThan(Integer length);
	 
	//Search Films where Rental Duration is lower than {rd}
	 List<Film> getFilmsByRentalDurationLessThan(Integer duration);
	 
	//Search Films where Rental Rate  is lower than {rate}
	 List<Film> getFilmsByRentalRateLessThan(Double rate); 
	 
	//Search Films where Length is  lower than {length}
	 List<Film> getFilmsByLengthLessThan(Integer length);
	 
	 //Search  Films which are Released between {from} and {to}
	 List<Film> getFilmsBetweenYears(Integer fromYear, Integer toYear);
	 
	 //Search Films where Rating is lower than {rating}
	 List<Film> getFilmsByRatingLessThan(String rating);
	 
	 //Search Films where Rating is  greater than {rating}
	 List<Film> getFilmsByRatingGreaterThan(String rating);
	 
	//Search Films where Language is {lang}
	 List<FilmDTO> getFilmsByLanguage(Language language);
	 
	//Display number of Films released  by each Year.
	 List<Object[]> getFilmCountByYear();
	 
	//Find all Actors of a Film by Film id
	 List<Actor> getActorsByFilmId(Integer filmId);
	 
	//Find all Films of specified  {category}
	 List<Film> findFilmsByCategory(String category);
	 
	//Assign  Actor to a Film
	 FilmActorDTO assignActorToFilm(Integer filmId, Integer actorId);

	//Update Title of a Film
	 FilmDTO updateFilmTitle(Integer filmId, String newTitle);
	 
	//Update Release Year of a Film
	 FilmDTO updateFilmReleaseYear(Integer filmId, Integer releaseYear);
	 
	//Update Rental Duration of a Film
	 FilmDTO updateFilmRentalDuration(Integer filmId, Short rentalDuration);
	 
	//Update Rental Rate of a Film
	 FilmDTO updateFilmRentalRate(Integer filmId, Double rentalRate);
	 
	//Update  Rating of a Film
	 FilmDTO updateFilmRating(Integer filmId, String rating);
	 
	//Update Language of a Film
	 Film updateLanguageName(Short languageId, String newLanguageName);
	 
	 //Update Category of a Film
//	 Category updateCategoryName(Short categoryId, String newName);
	 FilmCategoryDTO updateFilmCategory(Integer filmId, Integer categoryId);


}
