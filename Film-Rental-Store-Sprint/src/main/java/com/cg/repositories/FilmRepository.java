package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dto.FilmDTO;
import com.cg.model.Actor;
import com.cg.model.Film;
import com.cg.model.Film_Actor;
import com.cg.model.Language;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

	// Add new Film object in DB

	// Search Films by Title
	List<Film> findByTitle(String title);

	// Search Films by Release Year
	List<Film> findByReleaseYear(Integer releaseYear);

	// Search Films where Rental Duration is greater than {rd}
	List<Film> findByRentalDurationGreaterThan(Integer duration);

	// Search Films where Rental Rate is greater than {rate}
	List<Film> findByRentalRateGreaterThan(Double rentalRate);

	// Search Films where Length is greater than {length}
	List<Film> findByLengthGreaterThan(Integer length);

	// Search Films where Rental Duration is lower than {rd}
	List<Film> findByRentalDurationLessThan(Integer duration);

	// Search Films where Rental Rate is lower than {rate}
	List<Film> findByRentalRateLessThan(Double rentalRate);

	// Search Films where Length is lower than {length}
	List<Film> findByLengthLessThan(Integer length);

	// Search Films which are Released between {from} and {to}
	List<Film> findByReleaseYearBetween(Integer fromYear, Integer toYear);

	// Search Films where Rating is lower than {rating}
	List<Film> findByRatingLessThan(String rating);

	// Search Films where Rating is greater than {rating}
	List<Film> findByRatingGreaterThan(String rating);

	// Search Films where Language is {lang}
	List<Film> findByLanguage(Language language);

	// Display number of Films released by each Year
	@Query("SELECT f.releaseYear AS year, COUNT(f) AS count FROM Film f GROUP BY f.releaseYear")
	List<Object[]> countFilmsByYear();

	// Find all Actors of a Film by Film id
//	    @Query("SELECT fa.actor FROM Film_Actor fa WHERE fa.film.filmId = :filmId")
//	    List<ActorFilmDTO> findActorsByFilmId(@Param("filmId") Integer filmId);
	@Query("SELECT a FROM Actor a JOIN Film_Actor fa ON a.id = fa.actorId WHERE fa.filmId = :filmId")
	List<Actor> findActorsByFilmId(@Param("filmId") Integer filmId);

	// Find all Films of specified {category}
//	    @Query("SELECT f FROM Film f JOIN Film_Category fc ON f.filmId = fc.filmId JOIN Category c ON fc.categoryId = c.categoryId WHERE c.name = :categoryName")
//	    List<Film> findByCategoryName(String categoryName);
	@Query("SELECT f FROM Film f " +
		       "JOIN f.filmCategories fc " +
		       "JOIN fc.category c " +
		       "WHERE LOWER(c.name) = LOWER(:categoryName)")
		List<Film> findFilmsByCategory(@Param("categoryName") String categoryName);




	// Assign Actor to a Film
//	    @Query("INSERT INTO Film_Actor (film_id, actor_id, last_update) VALUES (:#{#filmActor.filmId}, :#{#filmActor.actorId}, :#{#filmActor.lastUpdate})")
	Film_Actor save(Film_Actor filmActor);

	// Update Title of a Film

	// Update Release Year of a Film

	// Update Rental Duration of a Film

	// Update Rental Rate of a Film

	// Update Rating of a Film

	// Update Language of a Film
	List<Film> getFilmsByLanguage(Language language);

	// Update Category of a Film

}
