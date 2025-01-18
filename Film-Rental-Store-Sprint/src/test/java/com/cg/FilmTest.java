package com.cg;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cg.controller.FilmController;
import com.cg.dto.FilmActorDTO;
import com.cg.dto.FilmDTO;
import com.cg.model.Film;
import com.cg.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class FilmTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmController).build();
    }

    @Test
    void testAddFilm() throws Exception {
        Film mockFilm = new Film();
        mockFilm.setTitle("Test Film");
        when(filmService.addFilm(any(Film.class))).thenReturn(mockFilm);

        mockMvc.perform(post("/api/films/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Film\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Film"));

        verify(filmService, times(1)).addFilm(any(Film.class));
    }

    @Test
    void testGetFilmsByTitle() throws Exception {
        Film mockFilm = new Film();
        mockFilm.setTitle("Test Film");
        List<Film> films = Arrays.asList(mockFilm);

        when(filmService.getFilmsByTitle("Test Film")).thenReturn(films);

        mockMvc.perform(get("/api/films/title/Test Film"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Film"));

        verify(filmService, times(1)).getFilmsByTitle("Test Film");
    }

    @Test
    void testGetFilmsByYear() throws Exception {
        Film mockFilm = new Film();
        mockFilm.setReleaseYear(2022);
        List<Film> films = Arrays.asList(mockFilm);

        when(filmService.getFilmsByYear(2022)).thenReturn(films);

        mockMvc.perform(get("/api/films/year/2022"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].releaseYear").value(2022));

        verify(filmService, times(1)).getFilmsByYear(2022);
    }

    @Test
    void testUpdateFilmTitle() throws Exception {
        FilmDTO mockFilmDTO = new FilmDTO();
        mockFilmDTO.setTitle("Updated Title");

        when(filmService.updateFilmTitle(anyInt(), anyString())).thenReturn(mockFilmDTO);

        mockMvc.perform(put("/api/films/update/title/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));

        verify(filmService, times(1)).updateFilmTitle(1, "Updated Title");
    }
    

}
