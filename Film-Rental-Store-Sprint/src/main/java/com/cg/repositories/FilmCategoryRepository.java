package com.cg.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.model.Film;
import com.cg.model.FilmCategoryId;
import com.cg.model.Film_Category;
//import com.cg.model.Film_Category.FilmCategoryId;

@Repository
public interface FilmCategoryRepository  {
	
//Optional<Film_Category> findById(FilmCategoryId id);
    
//    @Query("SELECT fc FROM FilmCategory fc WHERE fc.film.filmId = :filmId")
//    Optional<Film_Category> findByFilmId(@Param("filmId") Integer filmId);
//
//	Film_Category save(Film_Category filmCategory);
}
