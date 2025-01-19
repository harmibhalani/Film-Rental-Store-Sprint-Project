package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.FilmActorDTO;
import com.cg.dto.FilmCategoryDTO;
import com.cg.dto.FilmDTO;
import com.cg.dto.FilmDataDTO;
import com.cg.dto.LanguageDTO;
import com.cg.model.Film;
import com.cg.model.Language;
import com.cg.repositories.FilmRepository;
import com.cg.repositories.LanguageRepository;
import com.cg.service.CategoryService;
import com.cg.service.FilmService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	 @Autowired
	    private FilmRepository filmRepository;
	
	@Autowired
	   private LanguageRepository languageRepository;
	
	@Autowired
    private CategoryService categoryService;
	
	
	 //Add new Film object in DB
	  @PostMapping("/films/post")
	    public ResponseEntity<?> addFilm(@Valid @RequestBody Film film) {
	        try {
	            Film savedFilm = filmService.addFilm(film);
	            return ResponseEntity.ok(savedFilm);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error adding film: " + e.getMessage());
	        }
	    }
	
	// Search Films by Title
	  @GetMapping("/films/title/{title}")
	    public ResponseEntity<?> getFilmsByTitle(@PathVariable String title) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByTitle(title);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by title: " + e.getMessage());
	        }
	    }
//	  @GetMapping("/films/title/{title}")
//	    public ResponseEntity<?> getFilmsByTitle(@PathVariable String title) {
//	        List<FilmDataDTO> films = filmService.getFilmsByTitle(title);
//	        return ResponseEntity.ok(films);
//	    }


	  
	  
	// Search Films by Release Year
	  @GetMapping("/films/year/{year}")
	    public ResponseEntity<?> getFilmsByYear(@Valid @PathVariable Integer year) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByYear(year);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by year: " + e.getMessage());
	        }
	    }

	// Search Films where Rental Duration is greater than {rd}
	  @GetMapping("/films/duration/gt/{duration}")
	    public ResponseEntity<?> getFilmsByRentalDurationGreaterThan(@Valid @PathVariable Integer duration) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByRentalDurationGreaterThan(duration);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by rental duration: " + e.getMessage());
	        }
	    }

	// Search Films where Rental Rate is greater than {rate}
	  @GetMapping("/films/rate/gt/{rate}")
	    public ResponseEntity<?> getFilmsByRentalRateGreaterThan(@Valid @PathVariable Double rate) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByRentalRateGreaterThan(rate);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by rental rate: " + e.getMessage());
	        }
	    }

	// Search Films where Length is greater than {length}
	  @GetMapping("/films/length/gt/{length}")
	    public ResponseEntity<?> getFilmsByLengthGreaterThan(@Valid @PathVariable Integer length) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByLengthGreaterThan(length);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by length: " + e.getMessage());
	        }
	    }

	// Search Films where Rental Duration is lower than {rd}
	  @GetMapping("/films/duration/lt/{duration}")
	    public ResponseEntity<?> getFilmsByRentalDurationLessThan(@Valid @PathVariable Integer duration) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByRentalDurationLessThan(duration);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by rental duration: " + e.getMessage());
	        }
	    }

	// Search Films where Rental Rate is lower than {rate}
	  @GetMapping("/films/rate/lt/{rate}")
	    public ResponseEntity<?> getFilmsByRentalRateLessThan(@Valid @PathVariable Double rate) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByRentalRateLessThan(rate);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by rental rate: " + e.getMessage());
	        }
	    }

	// Search Films where Length is lower than {length}
	  @GetMapping("/films/length/lt/{length}")
	    public ResponseEntity<?> getFilmsByLengthLessThan(@Valid @PathVariable Integer length) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByLengthLessThan(length);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by length: " + e.getMessage());
	        }
	    }

	// Search Films which are Released between {from} and {to}
	  @GetMapping("/films/betweenyear/{from}/{to}")
	    public ResponseEntity<?> getFilmsBetweenYears(@Valid @PathVariable Integer from,@Valid @PathVariable Integer to) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsBetweenYears(from, to);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films between years: " + e.getMessage());
	        }
	    }
	
	//Search Films where Rating is lower than {rating}
	  @GetMapping("/films/rating/lt/{rating}")
	    public ResponseEntity<?> getFilmsByRatingLessThan(@Valid @PathVariable String rating) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByRatingLessThan(rating);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by rating: " + e.getMessage());
	        }
	    }
	
	//Search Films where Rating is  greater than {rating}
	  @GetMapping("/films/rating/gt/{rating}")
	    public ResponseEntity<?> getFilmsByRatingGreaterThan(@Valid @PathVariable String rating) {
	        try {
	            List<FilmDataDTO> films = filmService.getFilmsByRatingGreaterThan(rating);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by rating: " + e.getMessage());
	        }
	    }

	 
	 //Search Films where Language is {lang}
	  @GetMapping("/films/language/{langName}")
	    public ResponseEntity<?> getFilmsByLanguage(@Valid @PathVariable String langName) {
	        try {
	            Language language = languageRepository.findByName(langName);
	            if (language == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Language not found: " + langName);
	            }
	            List<FilmDataDTO> films = filmService.getFilmsByLanguage(language);
	            return ResponseEntity.ok(films);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching films by language: " + e.getMessage());
	        }
	    }
	 
	 //Display number of Films released  by each Year
	   @GetMapping("/films/countbyyear")
	    public ResponseEntity<?> getFilmCountByYear() {
	        try {
	            List<Object[]> filmCounts = filmService.getFilmCountByYear();
	            return ResponseEntity.ok(filmCounts);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error fetching film count by year: " + e.getMessage());
	        }
	    }
	 
//	//Find all Actors of a Film by Film id
	   @GetMapping("/films/{id}/actors")
	   public ResponseEntity<FilmDTO> getActorsByFilmId(@PathVariable Integer id) {
		   
		   FilmDTO filmActors = filmService.getActorsByFilmId(id);
		   
		   return ResponseEntity.ok(filmActors);
//	       try {
//	           List<Film> actors = filmService.getActorsByFilmId(id);
//	           return ResponseEntity.ok(actors);
//	       } catch (Exception e) {
//	           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//	                   .body("Error fetching actors by film ID: " + e.getMessage());
//	       }
	   }
	 
	 //Find all Films of specified  {category}
	   @GetMapping("/films/category/{category}")
	   public ResponseEntity<?> getFilmsByCategory(@PathVariable String category) {
	       try {
	           List<FilmCategoryDTO> films = filmService.findFilmsByCategory(category);
	           if (films.isEmpty()) {
	               return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                       .body("No films found for category: " + category);
	           }
	           return ResponseEntity.ok(films);
	       } catch (Exception e) {
	           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                   .body("Error fetching films by category: " + e.getMessage());
	       }
	   }


	 
	 //Assign  Actor to a Film
	   @PutMapping("/films/{filmId}/actor")
	    public ResponseEntity<?> assignActorToFilm(
	            @Valid @PathVariable Integer filmId,
	            @RequestBody FilmActorDTO filmActorDTO) {
	        try {
	            FilmActorDTO assignedActor = filmService.assignActorToFilm(filmId, filmActorDTO.getActorId());
	            return ResponseEntity.ok(assignedActor);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error assigning actor to film: " + e.getMessage());
	        }
	    }
	 
	 //Update Title of a Film
	   @PutMapping("/films/update/title/{id}")
	    public ResponseEntity<?> updateFilmTitle(
	             @PathVariable("id") Integer filmId,
	            @RequestBody FilmDTO filmDTO) {
	        try {
	            FilmDTO updatedFilm = filmService.updateFilmTitle(filmId, filmDTO.getTitle());
	            return ResponseEntity.ok(updatedFilm);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error updating film title: " + e.getMessage());
	        }
	    }
	 
	 //Update Release Year of a Film
	   @PutMapping("/films/update/releaseyear/{id}")
	    public ResponseEntity<?> updateFilmReleaseYear(
	            @Valid @PathVariable("id") Integer filmId,
	            @RequestBody FilmDTO filmDTO) {
	        try {
	            FilmDTO updatedFilm = filmService.updateFilmReleaseYear(filmId, filmDTO.getReleaseYear());
	            return ResponseEntity.ok(updatedFilm);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error updating film release year: " + e.getMessage());
	        }
	    }
	 
	 //Update Rental Duration of a Film
	   @PutMapping("/films/update/rentalduration/{id}")
	    public ResponseEntity<?> updateFilmRentalDuration(
	            @Valid @PathVariable("id") Integer filmId,
	            @RequestBody FilmDTO filmDTO) {
	        try {
	            FilmDTO updatedFilm = filmService.updateFilmRentalDuration(filmId, filmDTO.getRentalDuration());
	            return ResponseEntity.ok(updatedFilm);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error updating film rental duration: " + e.getMessage());
	        }
	    }
	 
	 //Update Rental Rate of a Film
	   @PutMapping("/films/update/rentalrate/{id}")
	    public ResponseEntity<?> updateFilmRentalRate(
	            @Valid @PathVariable("id") Integer filmId,
	            @RequestBody FilmDTO filmDTO) {
	        try {
	            FilmDTO updatedFilm = filmService.updateFilmRentalRate(filmId, filmDTO.getRentalRate());
	            return ResponseEntity.ok(updatedFilm);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error updating film rental rate: " + e.getMessage());
	        }
	    }
	
	 //Update  Rating of a Film
	   @PutMapping("/films/update/rating/{id}")
	    public ResponseEntity<?> updateFilmRating(
	            @Valid @PathVariable("id") Integer filmId,
	            @RequestBody FilmDTO filmDTO) {
	        try {
	            FilmDTO updatedFilm = filmService.updateFilmRating(filmId, filmDTO.getRating());
	            return ResponseEntity.ok(updatedFilm);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Error updating film rating: " + e.getMessage());
	        }
	    }
	 
	 //Update Language of a Film
	   @PutMapping("/films/update/language/{id}")
	   public ResponseEntity<?> updateLanguageName(
	           @PathVariable("id") Short languageId,
	           @RequestBody LanguageDTO request) {
	       try {
	           List<FilmDTO> updatedFilms = filmService.updateLanguageName(languageId, request.getName());
	           if (updatedFilms == null || updatedFilms.isEmpty()) {
	               return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                       .body("No films found for the updated language");
	           }
	           return ResponseEntity.ok(updatedFilms); // Return list of updated films
	       } catch (EntityNotFoundException e) {
	           return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                   .body("Language not found with id: " + languageId);
	       } catch (Exception e) {
	           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                   .body("Error updating language name: " + e.getMessage());
	       }
	   }

	 
	//Update Category of a Film
//	 @PutMapping("/films/update/category/{id}")
//	 public ResponseEntity<?> updateCategoryName(
//	         @PathVariable("id") Short categoryId,
//	         @RequestBody CategoryDTO request) {
//	     try {
//	         Category updatedCategory = categoryService.updateCategoryName(categoryId, request.getName());
//	         return ResponseEntity.ok(updatedCategory);
//	     } catch (EntityNotFoundException e) {
//	         return ResponseEntity.status(HttpStatus.NOT_FOUND)
//	                 .body("Category not found with id: " + categoryId);
//	     } catch (Exception e) {
//	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//	                 .body("Error updating category name: " + e.getMessage());
//	     }
//	 }
	   @PutMapping("/films/update/category/{id}")
	   public ResponseEntity<?> updateFilmCategory(
	           @PathVariable("id") Integer filmId,
	           @RequestBody FilmCategoryDTO filmCategoryDTO) {
	       try {
	           FilmCategoryDTO updatedCategory = filmService.updateFilmCategory(filmId, filmCategoryDTO.getCategoryId());
	           return ResponseEntity.ok(updatedCategory);
	       } catch (Exception e) {
	           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                   .body("Error updating film category: " + e.getMessage());
	       }
	   }
//	   @PutMapping("/films/update/category/{id}")
//	   public ResponseEntity<?> updateFilmCategory(
//	       @PathVariable("id") Integer filmId,
//	       @RequestBody FilmCategoryDTO filmCategoryDTO) {
//	       try {
//	           FilmCategoryDTO updatedCategory = filmService.updateFilmCategory(filmId, filmCategoryDTO.getCategoryId());
//	           return ResponseEntity.ok(updatedCategory);
//	       } catch (Exception e) {
//	           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//	                   .body("Error updating film category: " + e.getMessage());
//	       }
//	   }



}
