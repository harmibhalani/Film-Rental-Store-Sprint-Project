package com.cg;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.controller.ActorController;
import com.cg.dto.ActorDTO;
import com.cg.dto.FilmActorDTO;
//import com.cg.dto.UpdateActorFilmRequest;
import com.cg.exception.ActorAlreadyExistsException;
import com.cg.model.Actor;
import com.cg.service.ActorService;
 
class ActorTest {
 
    @InjectMocks
    private ActorController actorController;
 
    @Mock
    private ActorService actorService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
   
    @Test
    void testGetActorsByLastName_NullInput() {
        ResponseEntity<?> response = actorController.getActorsByLastName(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Last name must not be empty.", response.getBody());
    }
    
    @Test
    void testGetActorsByLastName_EmptyInput() {
        ResponseEntity<?> response = actorController.getActorsByLastName("");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Last name must not be empty.", response.getBody());
    }
    
    @Test
    void testGetAllActors_Empty() {
        when(actorService.getAllActors()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = actorController.getAllActors();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No actors found.", response.getBody());
    }
    
    @Test
    void testGetActorsByFirstName_EmptyInput() {
        ResponseEntity<?> response = actorController.getActorsByFirstName("");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("First name must not be empty.", response.getBody());
    }
    
    @Test
    void testGetActorsByLastName_NotFound() {
        when(actorService.getActorsByLastName("Unknown")).thenReturn(Collections.emptyList());
 
        ResponseEntity<?> response = actorController.getActorsByLastName("Unknown");
 
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No actors found with the last name: Unknown", response.getBody());
    }
 
    @Test
    void testCreateActor_Success() {
        ActorDTO mockActorDTO = new ActorDTO();
        mockActorDTO.setFirstName("John");
        mockActorDTO.setLastName("Doe");
 
        when(actorService.createActor(mockActorDTO)).thenReturn(mockActorDTO);
 
        ResponseEntity<?> response = actorController.createActor(mockActorDTO);
 
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockActorDTO, response.getBody());
    }
 
    @Test
    void testCreateActor_Conflict() {
        ActorDTO mockActorDTO = new ActorDTO();
        mockActorDTO.setFirstName("John");
        mockActorDTO.setLastName("Doe");
 
        when(actorService.createActor(mockActorDTO)).thenThrow(new ActorAlreadyExistsException("Actor already exists"));
 
        ResponseEntity<?> response = actorController.createActor(mockActorDTO);
 
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Actor already exists", response.getBody());
    }
 
//    @Test
//    void testUpdateLastName_Success() {
//        UpdateActorFilmRequest request = new UpdateActorFilmRequest();
//        request.setLastName("Smith");
// 
//        ActorDTO updatedActorDTO = new ActorDTO();
//        updatedActorDTO.setLastName("Smith");
// 
//        when(actorService.updateLastName(1, "Smith")).thenReturn(updatedActorDTO);
// 
//        ResponseEntity<ActorDTO> response = actorController.updateLastName(1, request);
// 
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Smith", response.getBody().getLastName());
//    }
 
    @Test
    void testAssignFilmToActor_Success() {
        FilmActorDTO filmActorDTO = new FilmActorDTO();
        filmActorDTO.setFilmId(10);
 
        when(actorService.assignFilmToActor(1, 10)).thenReturn(filmActorDTO);
 
        ResponseEntity<?> response = actorController.assignFilmToActor(1, filmActorDTO);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filmActorDTO, response.getBody());
    }
}
 