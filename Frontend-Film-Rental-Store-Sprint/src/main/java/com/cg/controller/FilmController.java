package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.model.Film;
import com.cg.service.FilmService;

@Controller
@RequestMapping("/homePage/dashboard/filmManagement")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	//Get all films
	@GetMapping("/all-films")
    public ResponseEntity<?> getAllFilms() {
        try {
            List<Film> films = filmService.getAllFilms();
            if (films.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No films found.");
            }
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching films: " + e.getMessage());
        }
    }
	
	//Get method to search film by title and year
	@GetMapping("/search-film")
	public ResponseEntity<?> searchFilms(@RequestParam String searchType, @RequestParam String searchTerm) {
		try {
			List<Film> results;
			switch (searchType) {
			case "title":
				results = filmService.searchByTitle(searchTerm);
				break;
			case "year":
				int year = Integer.parseInt(searchTerm);
				results = filmService.searchByYear(year);
				break;
			default:
				return ResponseEntity.badRequest().body("Invalid search type");
			}
			return ResponseEntity.ok(results);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("Invalid year format");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error performing search: " + e.getMessage());
		}
	}

	// PUT request to update the title of a film
	@PutMapping("/update/title/{id}")
	public ResponseEntity<?> updateFilmTitle(@PathVariable("id") Integer filmId, @RequestBody Film filmDTO) {
		try {
			Film updatedFilm = filmService.updateFilmTitle(filmId, filmDTO);
			return ResponseEntity.ok(updatedFilm);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating film: " + e.getMessage());
		}
	}

	// PUT request to update the release year of a film
	@PutMapping("/update/releaseyear/{id}")
	public ResponseEntity<?> updateByReleaseYear(@PathVariable("id") Integer filmId, @RequestBody Film filmDTO) {
		try {
			Film updatedFilm = filmService.updateByReleaseYear(filmId, filmDTO);
			return ResponseEntity.ok(updatedFilm);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error updating film release year: " + e.getMessage());
		}
	}
}
