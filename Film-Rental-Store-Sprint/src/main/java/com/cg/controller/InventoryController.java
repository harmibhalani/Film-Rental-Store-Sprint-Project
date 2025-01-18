package com.cg.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.exception.BadRequestException;
import com.cg.exception.InventoryNotFoundException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.dto.InventoryDTO;
import com.cg.service.InventoryService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api")
public class InventoryController {
 
	
    @Autowired
    private InventoryService inventoryService;
 
    // Display inventory(count) of all Films in all Stores
    @GetMapping("/inventory/films/{title}")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByFilmTitle(@PathVariable String title) {
        List<InventoryDTO> inventories = inventoryService.getInventoriesByFilmTitle(title);
        if (inventories.isEmpty()) {
        	 throw new InventoryNotFoundException("No inventories found for film title: " + title);
        }

        return ResponseEntity.ok(inventories);
    }

    //Display inventory of all Films by a Store
    @GetMapping("/inventory/store/{id}/films")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByStoreId(@PathVariable Short id) {
        List<InventoryDTO> inventories = inventoryService.getInventoriesByStoreId(id);
        if (inventories.isEmpty()) {
            throw new InventoryNotFoundException("No inventories found for store ID: " + id);
        }
 
        return ResponseEntity.ok(inventories);
    }
 
    //Display inventory(count) of a Film in all Stores
    @GetMapping("/inventory/film/{id}/films")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByFilmId(@PathVariable Integer id) {
        if (id <= 0) {
            throw new BadRequestException("Film ID must be a positive number");
        }
        try {
            List<InventoryDTO> inventories = inventoryService.getInventoriesByFilmId(id);
            if (inventories.isEmpty()) {
                throw new ResourceNotFoundException("No inventories found for film ID: " + id);
            }
            return ResponseEntity.ok(inventories);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving inventories for film ID: " + id, e);
        }
    }

    //Display inventory(count) of a Film in a Store
    @GetMapping("/inventory/film/{filmId}/store/{storeId}")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByFilmIdAndStoreId(@PathVariable Integer filmId, @PathVariable Short storeId) {
        List<InventoryDTO> inventories = inventoryService.getInventoriesByFilmIdAndStoreId(filmId, storeId);
        if (inventories.isEmpty()) {
            throw new InventoryNotFoundException("No inventories found for film ID: " + filmId + " and store ID: " + storeId);
        }
        return ResponseEntity.ok(inventories);
    }
 
    
    //Add one more Film to a Store
    @PostMapping("/inventory/add")
    public ResponseEntity<?> addInventory(@RequestBody @Valid InventoryDTO inventoryDTO) {
List<InventoryDTO> existingInventories = inventoryService.getInventoriesByFilmTitle(inventoryDTO.getFilmTitle());
        if (existingInventories.isEmpty()) {
            // Return a custom message instead of an internal server error
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(List.of(new InventoryDTO(null, "No film found by this title", null, null)));
        }
    	try {
            InventoryDTO createdInventory = inventoryService.addInventory(inventoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle any exceptions during creation
        }
    }
 
   
}