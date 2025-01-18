package com.cg;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import com.cg.controller.InventoryController;
import com.cg.dto.InventoryDTO;
import com.cg.service.InventoryService;
 
public class InventoryTest {
 
    @InjectMocks
    private InventoryController inventoryController;
 
    @Mock
    private InventoryService inventoryService;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
    }
 
  //Add one more Film to a Store
    @Test
    public void testAddInventory_Success() throws Exception {
        // Create a valid InventoryDTO
        InventoryDTO inventoryDTO = new InventoryDTO(1, "Inception", (short) 1, LocalDateTime.now());
 
        // Mock the service layer
        when(inventoryService.getInventoriesByFilmTitle("Inception")).thenReturn(Collections.singletonList(inventoryDTO));
        when(inventoryService.addInventory(any(InventoryDTO.class))).thenReturn(inventoryDTO);
 
        // Perform the POST request and validate the response
        mockMvc.perform(post("/api/inventory/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"inventoryId\":1,\"filmTitle\":\"Inception\",\"storeId\":1,\"lastUpdate\":\"2025-01-17T13:00:00\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.filmTitle").value("Inception"))
                .andExpect(jsonPath("$.storeId").value(1));
        // Verify that the service method was called
        verify(inventoryService).addInventory(any(InventoryDTO.class));
    }
    // Display inventory(count) of all Films in all Stores
    @Test
    public void testGetInventoriesByFilmTitle_Success() throws Exception {
        String filmTitle = "Inception";
        List<InventoryDTO> inventories = Collections.singletonList(new InventoryDTO(1, filmTitle, (short) 1, LocalDateTime.now()));
 
        // Mock the service layer
        when(inventoryService.getInventoriesByFilmTitle(filmTitle)).thenReturn(inventories);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/inventory/films/{title}", filmTitle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].filmTitle").value(filmTitle));
        // Verify that the service method was called with correct parameters
        verify(inventoryService).getInventoriesByFilmTitle(filmTitle);
    }
 
  //Display inventory of all Films by a Store
    @Test
    public void testGetInventoriesByStoreId_Success() throws Exception {
        Short storeId = 1; // Ensure this is a Short
        List<InventoryDTO> inventories = Collections.singletonList(new InventoryDTO(1, "Inception", storeId, LocalDateTime.now()));
 
        // Mock the service layer
        when(inventoryService.getInventoriesByStoreId(storeId)).thenReturn(inventories);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/inventory/store/{id}/films", storeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].storeId").value(storeId.intValue())) // Convert Short to int for comparison
                .andExpect(jsonPath("$[0].filmTitle").value("Inception")); // Check film title
        // Verify that the service method was called with correct parameters
        verify(inventoryService).getInventoriesByStoreId(storeId);
    }
 
 
  //Display inventory(count) of a Film in all Stores
    @Test
    public void testGetInventoriesByFilmId_Success() throws Exception {
        Integer filmId = 1;
        List<InventoryDTO> inventories = Collections.singletonList(new InventoryDTO(1, "Inception", (short) 1, LocalDateTime.now()));
 
        // Mock the service layer
        when(inventoryService.getInventoriesByFilmId(filmId)).thenReturn(inventories);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/inventory/film/{id}/films", filmId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].inventoryId").value(1));
        // Verify that the service method was called with correct parameters
        verify(inventoryService).getInventoriesByFilmId(filmId);
    }
 
  //Display inventory(count) of a Film in a Store
    @Test
    public void testGetInventoriesByFilmIdAndStoreId_Success() throws Exception {
        Integer filmId = 1;
        Short storeId = 1;
        List<InventoryDTO> inventories = Collections.singletonList(new InventoryDTO(1, "Inception", storeId, LocalDateTime.now()));
 
        // Mock the service layer
        when(inventoryService.getInventoriesByFilmIdAndStoreId(filmId, storeId)).thenReturn(inventories);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/inventory/film/{filmId}/store/{storeId}", filmId, storeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].filmTitle").value("Inception"));
        // Verify that the service method was called with correct parameters
        verify(inventoryService).getInventoriesByFilmIdAndStoreId(filmId, storeId);
    }
 
    
}
