package com.cg.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ActorDTO;
import com.cg.dto.FilmActorDTO;
import com.cg.dto.FilmCategoryDTO;
import com.cg.dto.FilmDTO;
import com.cg.dto.FilmDataDTO;
import com.cg.exception.InvalidInputException;
import com.cg.model.Actor;
import com.cg.model.Category;
import com.cg.model.Film;
import com.cg.model.Film_Category;
import com.cg.model.Language;
import com.cg.repositories.ActorRepository;
import com.cg.repositories.CategoryRepository;
import com.cg.repositories.FilmRepository;
import com.cg.repositories.LanguageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private ActorRepository actorRepository;

	// @Autowired
	// private FilmCategoryRepository filmCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@PersistenceContext
	private EntityManager entityManager;

	// Add new Film object in DB
	@Override
	public Film addFilm(Film film) {
		return filmRepository.save(film);
	}

	// Search Films by Title
	// public List<Film> getFilmsByTitle(String title) {
	// return filmRepository.findByTitle(title);
	// }
	@Override
	public List<FilmDataDTO> getFilmsByTitle(String title) {
		List<Film> films = filmRepository.findByTitle(title);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			// dto.setLanguageId(film.getLanguage());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films by Release Year
	public List<FilmDataDTO> getFilmsByYear(Integer year) {
		List<Film> films = filmRepository.findByReleaseYear(year);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Rental Duration is greater than {rd}
	public List<FilmDataDTO> getFilmsByRentalDurationGreaterThan(Integer duration) {
		List<Film> films = filmRepository.findByRentalDurationGreaterThan(duration);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Rental Rate is greater than {rate}
	public List<FilmDataDTO> getFilmsByRentalRateGreaterThan(Double rate) {
		List<Film> films = filmRepository.findByRentalRateGreaterThan(rate);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Length is greater than {length}
	public List<FilmDataDTO> getFilmsByLengthGreaterThan(Integer length) {
		List<Film> films = filmRepository.findByLengthGreaterThan(length);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Rental Duration is lower than {rd}
	public List<FilmDataDTO> getFilmsByRentalDurationLessThan(Integer duration) {
		List<Film> films = filmRepository.findByRentalDurationLessThan(duration);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Rental Rate is lower than {rate}
	public List<FilmDataDTO> getFilmsByRentalRateLessThan(Double rate) {
		List<Film> films = filmRepository.findByRentalRateLessThan(rate);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Length is lower than {length}
	public List<FilmDataDTO> getFilmsByLengthLessThan(Integer length) {
		List<Film> films = filmRepository.findByLengthLessThan(length);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films which are Released between {from} and {to}
	public List<FilmDataDTO> getFilmsBetweenYears(Integer fromYear, Integer toYear) {
		List<Film> films = filmRepository.findByReleaseYearBetween(fromYear, toYear);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Rating is lower than {rating}
	public List<FilmDataDTO> getFilmsByRatingLessThan(String rating) {
		List<Film> films = filmRepository.findByRatingLessThan(rating);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Rating is greater than {rating}
	public List<FilmDataDTO> getFilmsByRatingGreaterThan(String rating) {
		List<Film> films = filmRepository.findByRatingGreaterThan(rating);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
	}

	// Search Films where Language is {lang}
	public List<FilmDataDTO> getFilmsByLanguage(Language language) {
		List<Film> films = filmRepository.findByLanguage(language);

		List<FilmDataDTO> filmDTOs = new ArrayList<>();
		for (Film film : films) {
			FilmDataDTO dto = new FilmDataDTO();
			dto.setFilmId(film.getFilmId());
			dto.setTitle(film.getTitle());
			dto.setDescription(film.getDescription());
			dto.setReleaseYear(film.getReleaseYear());
			dto.setRentalRate(film.getRentalRate());
			dto.setRating(film.getRating());
			dto.setRentalDuration(film.getRentalDuration());
			dto.setLength(film.getLength());
			filmDTOs.add(dto);
		}
		return filmDTOs;
		// return films.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private FilmDTO convertToDTO(Film film) {
		return new FilmDTO( film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage(),
                        film.getOriginalLanguageId(),
                        film.getRentalRate(),
                        film.getRating(),
                        film.getRentalDuration(),
                        film.getLength(),
                        film.getSpecialFeatures(),
                        film.getReplacementCost() );
	}

	// Display number of Films released by each Year
	@Override
	public List<Object[]> getFilmCountByYear() {
		return filmRepository.countFilmsByYear();
	}

	// Find all Actors of a Film by Film id
	// @Override
	// public List<ActorFilmDTO> getActorsByFilmId(Integer filmId) {
	// List<ActorFilmDTO> actors = filmRepository.findActorsByFilmId(filmId); //
	// Retrieve Actors from Repository
	//
	// List<ActorFilmDTO> actorDTOs = new ArrayList<>();
	// for (ActorFilmDTO actor : actors) {
	// ActorFilmDTO actorDTO = new ActorFilmDTO(
	// actor.getFilmId(),
	// actor.getActorId(),
	// actor.getActorFirstName(),
	// actor.getActorLastName());
	// actorDTOs.add(actorDTO);
	// }
	//
	// return actorDTOs;
	// }
	@Override
	public FilmDTO getActorsByFilmId(Integer filmId) {
		Film film = filmRepository.findById(filmId).orElseThrow();

		List<Actor> actors = filmRepository.findActorsByFilmId(filmId);

		FilmDTO filmDTO = new FilmDTO( film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage(),
                film.getOriginalLanguageId(),
                film.getRentalRate(),
                film.getRating(),
                film.getRentalDuration(),
                film.getLength(),
                film.getSpecialFeatures(),
                film.getReplacementCost() );

		List<ActorDTO> actorDTOs = actors.stream().map(
				actor -> new ActorDTO(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getLastUpdate()))
				.collect(Collectors.toList());

		filmDTO.setActors(actorDTOs);
		return filmDTO;
	}
	


// Find all Films of specified {category}
	@Override
	public List<FilmCategoryDTO> findFilmsByCategory(String category) {
	    if (category == null || category.trim().isEmpty()) {
	        throw new IllegalArgumentException("Category cannot be null or empty");
	    }

	    List<Film> films = filmRepository.findFilmsByCategory(category);

	    List<FilmCategoryDTO> filmCategoryDTOs = new ArrayList<>();

	    for (Film film : films) {
	        for (Film_Category filmCategory : film.getFilmCategories()) {
	            if (filmCategory.getCategory().getName().equalsIgnoreCase(category)) {
	                FilmCategoryDTO dto = new FilmCategoryDTO();
	                dto.setFilmId(film.getFilmId().longValue());  // Convert Integer to Long
	                dto.setCategoryId(filmCategory.getCategory().getCategoryId().longValue());  // Convert Integer to Long
	                dto.setCategoryName(filmCategory.getCategory().getName());
	                dto.setLastUpdate(film.getLastUpdate());

	                filmCategoryDTOs.add(dto);
	            }
	        }
	    }

	    return filmCategoryDTOs;
	}



//Assign  Actor to a Film
	@Override
	public FilmActorDTO assignActorToFilm(Integer filmId, Integer actorId) {
		String checkQuery = "SELECT COUNT(*) FROM film_actor WHERE film_id = :filmId AND actor_id = :actorId";
		Query query = entityManager.createNativeQuery(checkQuery);
		query.setParameter("filmId", filmId);
		query.setParameter("actorId", actorId);

		Long count = ((Number) query.getSingleResult()).longValue();
		if (count > 0) {
			throw new InvalidInputException("Actor is already assigned to this film");
		}

		Film film = filmRepository.findById(filmId).orElseThrow();

		Actor actor = actorRepository.findById(actorId).orElseThrow();

		String insertQuery = "INSERT INTO film_actor (actor_id, film_id, last_update) VALUES (:actorId, :filmId, :lastUpdate)";
		Query insertNativeQuery = entityManager.createNativeQuery(insertQuery);
		insertNativeQuery.setParameter("actorId", actorId);
		insertNativeQuery.setParameter("filmId", filmId);
		insertNativeQuery.setParameter("lastUpdate", LocalDateTime.now());
		insertNativeQuery.executeUpdate();

		return new FilmActorDTO(actorId, filmId, actor.getFirstName(), actor.getLastName());
	}

//Update Title of a Film
	@Override
	public FilmDTO updateFilmTitle(Integer filmId, String newTitle) {
		if (newTitle == null || newTitle.trim().isEmpty()) {
			throw new InvalidInputException("New title cannot be null or empty");
		}

		Film film = filmRepository.findById(filmId).orElseThrow();

		film.setTitle(newTitle);
		Film updatedFilm = filmRepository.save(film);

		return convertToDTO(updatedFilm);
	}

//Update Release Year of a Film
	@Override
	public FilmDTO updateFilmReleaseYear(Integer filmId, Integer releaseYear) {
		// Validate film exists
		Film film = filmRepository.findById(filmId)
				.orElseThrow(() -> new RuntimeException("Film not found with id: " + filmId));

		// Validate release year
		if (releaseYear == null) {
			throw new RuntimeException("Release year cannot be null");
		}

		// Update release year
		film.setReleaseYear(releaseYear);

		// Save the updated film
		Film updatedFilm = filmRepository.save(film);

		// Convert to DTO and return
		return convertToDTO(updatedFilm);
	}

	private FilmDTO convertToFilmDTO1(Film film) {
		return new FilmDTO( film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage(),
                film.getOriginalLanguageId(),
                film.getRentalRate(),
                film.getRating(),
                film.getRentalDuration(),
                film.getLength(),
                film.getSpecialFeatures(),
                film.getReplacementCost() );
	}

//Update Rental Duration of a Film
	@Override
	public FilmDTO updateFilmRentalDuration(Integer filmId, Short rentalDuration) {
		// Validate film exists
		Film film = filmRepository.findById(filmId)
				.orElseThrow(() -> new RuntimeException("Film not found with id: " + filmId));

		// Validate rental duration
		if (rentalDuration == null) {
			throw new RuntimeException("Rental duration cannot be null");
		}

		// Additional validation for rental duration
		if (rentalDuration < 1) {
			throw new RuntimeException("Rental duration must be at least 1 day");
		}

		// Update rental duration
		film.setRentalDuration(rentalDuration);

		// Save the updated film
		Film updatedFilm = filmRepository.save(film);

		// Convert to DTO and return
		return convertToDTO(updatedFilm);
	}

//Update Rental Rate of a Film
	@Override
	public FilmDTO updateFilmRentalRate(Integer filmId, BigDecimal rentalRate) {
		if (rentalRate == null || rentalRate.compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidInputException("Rental rate must be a positive number");
		}

		Film film = filmRepository.findById(filmId).orElseThrow();

		film.setRentalRate(rentalRate);
		Film updatedFilm = filmRepository.save(film);

		return convertToDTO(updatedFilm);
	}

//Update  Rating of a Film
	@Override
	public FilmDTO updateFilmRating(Integer filmId, String rating) {
		Film film = filmRepository.findById(filmId).orElseThrow();

		// Validate rating
		if (rating == null || !isValidRating(rating)) {
			throw new IllegalArgumentException("Invalid rating. Must be one of: G, PG, PG-13, R, NC-17");
		}

		// Update rating
		film.setRating(rating);
		Film updatedFilm = filmRepository.save(film);

		// Convert to DTO and return
		return new FilmDTO( updatedFilm.getFilmId(),
				updatedFilm.getTitle(),
				updatedFilm.getDescription(),
				updatedFilm.getReleaseYear(),
				updatedFilm.getLanguage(),
				updatedFilm.getOriginalLanguageId(),
				updatedFilm.getRentalRate(),
                updatedFilm.getRating(),
                updatedFilm.getRentalDuration(),
                updatedFilm.getLength(),
                updatedFilm.getSpecialFeatures(),
                updatedFilm.getReplacementCost() );
	}

	private boolean isValidRating(String rating) {
		return Arrays.asList("G", "PG", "PG-13", "R", "NC-17").contains(rating);
	}

//Update Language of a Film
	@Override
	public List<FilmDTO> updateLanguageName(Short languageId, String newLanguageName) {
	    // Find the language first
	    Language language = languageRepository.findById(languageId).orElseThrow();

	    // Update the language name
	    language.setName(newLanguageName);
	    Language updatedLanguage = languageRepository.save(language);

	    // Find films using the updated language
	    List<Film> films = filmRepository.getFilmsByLanguage(updatedLanguage);
	    
	    // Map the Film entities to FilmDTOs
	    List<FilmDTO> filmDTOs = films.stream().map(film -> new FilmDTO(
	            film.getFilmId(),
	            film.getTitle(),
	            film.getDescription(),
	            film.getReleaseYear(),
	            film.getLanguage(),
	            film.getOriginalLanguageId(),
	            film.getRentalRate(),
	            film.getRating(),
	            film.getRentalDuration(),
	            film.getLength(),
	            film.getSpecialFeatures(),
	            film.getReplacementCost())
	    ).collect(Collectors.toList());

	    return filmDTOs; // Return list of FilmDTOs
	}

	

//Update Category of a Film
//    @Override
//    public Category updateCategoryName(Short categoryId, String newName) {
//        // Fetch the category by ID
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow();
//
//        // Update the category name
//        category.setName(newName);
//        category.setLastUpdate(LocalDateTime.now());
//
//        // Save the updated category
//        return categoryRepository.save(category);
//    }
	@Override
	@Transactional
	public FilmCategoryDTO updateFilmCategory(Integer filmId, Long categoryId) {

		// Check if the film-category relation already exists
		String checkQuery = "SELECT COUNT(*) FROM film_category WHERE film_id = :filmId AND category_id = :categoryId";
		Query query = entityManager.createNativeQuery(checkQuery);
		query.setParameter("filmId", filmId);
		query.setParameter("categoryId", categoryId);

		Long count = ((Number) query.getSingleResult()).longValue();
		if (count > 0) {
			throw new InvalidInputException("Film is already assigned to this category");
		}

		// Delete existing category for the film (if any)
		String deleteQuery = "DELETE FROM film_category WHERE film_id = :filmId";
		Query deleteNativeQuery = entityManager.createNativeQuery(deleteQuery);
		deleteNativeQuery.setParameter("filmId", filmId);
		deleteNativeQuery.executeUpdate();

		// Insert new category
		String insertQuery = "INSERT INTO film_category (film_id, category_id, last_update) VALUES (:filmId, :categoryId, :lastUpdate)";
		Query insertNativeQuery = entityManager.createNativeQuery(insertQuery);
		insertNativeQuery.setParameter("filmId", filmId);
		insertNativeQuery.setParameter("categoryId", categoryId);
		insertNativeQuery.setParameter("lastUpdate", LocalDateTime.now());
		insertNativeQuery.executeUpdate();

		return new FilmCategoryDTO();
	}
//    @Override
//    public FilmCategoryDTO updateFilmCategory(Integer filmId, Integer categoryId) {
//        // Check if film exists
//        Film film = filmRepository.findById(filmId)
//                .orElseThrow();
//
//        // Check if category exists
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow();
//
//        // Create the composite key
//        FilmCategoryId filmCategoryId = new FilmCategoryId();
//
//        // Check if the film-category relationship already exists
//        Film_Category filmCategory = filmCategoryRepository.findById(filmCategoryId)
//                .orElse(new Film_Category());
//
//        // Set up the relationship
//        filmCategory.setId(filmCategoryId);
//        filmCategory.setFilm(film);
//        filmCategory.setCategory(category);
//        filmCategory.setLastUpdate(LocalDateTime.now());
//
//        // Save the relationship
//        try {
//            filmCategory = filmCategoryRepository.save(filmCategory);
//        } catch (DataIntegrityViolationException e) {
//            throw new InvalidInputException("Error saving film category relationship: " + e.getMessage());
//        }
//
//        // Return the DTO
//        return new FilmCategoryDTO(
//            filmId,
//            categoryId,
//            category.getName()
//        );
//    }

}
