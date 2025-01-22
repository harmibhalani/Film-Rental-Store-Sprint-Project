package com.cg;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.FilmController;
import com.cg.dto.*;
import com.cg.model.Film;
import com.cg.model.Language;
import com.cg.repositories.FilmRepository;
import com.cg.repositories.LanguageRepository;
import com.cg.service.CategoryService;
import com.cg.service.FilmService;

class FilmTest {

    @Mock
    private FilmService filmService;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private FilmController filmController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFilm() {
        Film film = new Film();
        when(filmService.addFilm(any(Film.class))).thenReturn(film);

        ResponseEntity<?> response = filmController.addFilm(film);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(film, response.getBody());
    }

    @Test
    void testGetFilmsByTitle() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByTitle(anyString())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByTitle("Test Title");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByYear() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByYear(anyInt())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByYear(2024);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByRentalDurationGreaterThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByRentalDurationGreaterThan(anyInt())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByRentalDurationGreaterThan(5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByRentalRateGreaterThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByRentalRateGreaterThan(anyDouble())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByRentalRateGreaterThan(4.99);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByLengthGreaterThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByLengthGreaterThan(anyInt())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByLengthGreaterThan(120);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByRentalDurationLessThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByRentalDurationLessThan(anyInt())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByRentalDurationLessThan(3);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByRentalRateLessThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByRentalRateLessThan(anyDouble())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByRentalRateLessThan(2.99);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByLengthLessThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByLengthLessThan(anyInt())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByLengthLessThan(90);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsBetweenYears() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsBetweenYears(anyInt(), anyInt())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsBetweenYears(2000, 2010);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByRatingLessThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByRatingLessThan(anyString())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByRatingLessThan("PG");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByRatingGreaterThan() {
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(filmService.getFilmsByRatingGreaterThan(anyString())).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByRatingGreaterThan("PG");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

    @Test
    void testGetFilmsByLanguage() {
        Language language = new Language();
        List<FilmDataDTO> films = Arrays.asList(new FilmDataDTO());
        when(languageRepository.findByName(anyString())).thenReturn(language);
        when(filmService.getFilmsByLanguage(any(Language.class))).thenReturn(films);

        ResponseEntity<?> response = filmController.getFilmsByLanguage("English");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(films, response.getBody());
    }

//    @Test
//    void testGetFilmCountByYear() {
//        List<Object> filmCounts = Arrays.asList(new Object[]{"2024", 10L});
//        when(filmService.getFilmCountByYear()).thenReturn(filmCounts);
//
//        ResponseEntity<?> response = filmController.getFilmCountByYear();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(filmCounts, response.getBody());
//    }

    @Test
    void testGetActorsByFilmId() {
        FilmDTO filmDTO = new FilmDTO();
        when(filmService.getActorsByFilmId(anyInt())).thenReturn(filmDTO);

        ResponseEntity<FilmDTO> response = filmController.getActorsByFilmId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filmDTO, response.getBody());
    }



    @Test
    void testUpdateFilmTitle() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle("New Title");
        when(filmService.updateFilmTitle(anyInt(), anyString())).thenReturn(filmDTO);

        ResponseEntity<?> response = filmController.updateFilmTitle(1, filmDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filmDTO, response.getBody());
    }

    @Test
    void testUpdateFilmReleaseYear() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setReleaseYear(2024);
        when(filmService.updateFilmReleaseYear(anyInt(), anyInt())).thenReturn(filmDTO);

        ResponseEntity<?> response = filmController.updateFilmReleaseYear(1, filmDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filmDTO, response.getBody());
    }



    @Test
    void testUpdateFilmRating() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setRating("PG-13");
        when(filmService.updateFilmRating(anyInt(), anyString())).thenReturn(filmDTO);

        ResponseEntity<?> response = filmController.updateFilmRating(1, filmDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filmDTO, response.getBody());
    }

    @Test
    void testUpdateLanguageName() {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("Spanish");
        List<FilmDTO> updatedFilms = Arrays.asList(new FilmDTO());
        when(filmService.updateLanguageName(anyShort(), anyString())).thenReturn(updatedFilms);

        ResponseEntity<?> response = filmController.updateLanguageName((short)1, languageDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFilms, response.getBody());
    }

}