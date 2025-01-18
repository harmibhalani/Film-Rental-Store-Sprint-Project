package com.cg.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.exception.InventoryNotFoundException;
import com.cg.dto.InventoryDTO;
import com.cg.model.Film;
import com.cg.model.Inventory;
import com.cg.model.Store;
import com.cg.repositories.FilmRepository;
import com.cg.repositories.InventoryRepository;
import com.cg.repositories.StoreRepository;
 
@Service
public class InventoryServiceImpl implements InventoryService {
 
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private FilmRepository filmRepository; 
    @Autowired
    private StoreRepository storeRepository;
 
    
    // Display inventory(count) of all Films in all Stores
    @Override
    public List<InventoryDTO> getInventoriesByFilmTitle(String title) {
        List<Inventory> inventories = inventoryRepository.findByFilm_Title(title);
        // Convert List<Inventory> to List<InventoryDTO>
        return inventories.stream()
        	    .map(inventory -> new InventoryDTO(
        	        inventory.getInventoryId(),
        	        inventory.getFilm().getTitle(), 
        	        inventory.getStore().getStoreId(), 
        	        inventory.getLastUpdate()))
        	    .collect(Collectors.toList());
    }
  //Display inventory of all Films by a Store
    @Override
    public List<InventoryDTO> getInventoriesByStoreId(Short storeId) {
        List<Inventory> inventories = inventoryRepository.findByStore_StoreId(storeId);
        return inventories.stream()
            .map(inventory -> new InventoryDTO(
                inventory.getInventoryId(),
                inventory.getFilm().getTitle(), 
                inventory.getStore().getStoreId(), 
                inventory.getLastUpdate()))
            .collect(Collectors.toList());
    }
  //Display inventory(count) of a Film in all Stores
    @Override
    public List<InventoryDTO> getInventoriesByFilmId(Integer filmId) {
        List<Inventory> inventories = inventoryRepository.findByFilm_FilmId(filmId); 
        if (inventories.isEmpty()) {
            throw new InventoryNotFoundException("No inventories found for film ID: " + filmId);
        }
        return inventories.stream()
            .map(inventory -> new InventoryDTO(
                inventory.getInventoryId(),
                inventory.getFilm().getTitle(), 
                inventory.getStore().getStoreId(), 
                inventory.getLastUpdate()))
            .collect(Collectors.toList());
    }
  //Display inventory(count) of a Film in a Store
    @Override
    public List<InventoryDTO> getInventoriesByFilmIdAndStoreId(Integer filmId, Short storeId) {
        List<Inventory> inventories = inventoryRepository.findByFilm_FilmIdAndStore_StoreId(filmId, storeId); 
        return inventories.stream()
            .map(inventory -> new InventoryDTO(
                inventory.getInventoryId(),
                inventory.getFilm().getTitle(), 
                inventory.getStore().getStoreId(), 
                inventory.getLastUpdate()))
            .collect(Collectors.toList());
    }


 
 
  //Add one more Film to a Store
    @Override
    public InventoryDTO addInventory(InventoryDTO inventoryDTO) {
        List<Film> films = filmRepository.findByTitle(inventoryDTO.getFilmTitle());
        if (films.isEmpty()) {
            throw new RuntimeException("Film not found with title: " + inventoryDTO.getFilmTitle());
        }
        Film film = films.get(0);  // Assuming you're taking the first match
        // Fetch Store by StoreId
        Store store = storeRepository.findByStoreId(inventoryDTO.getStoreId());
        // Create Inventory entity
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        inventory.setLastUpdate(inventoryDTO.getLastUpdate());
 
        // Save Inventory to the database
        Inventory savedInventory = inventoryRepository.save(inventory);
 
        // Return InventoryDTO
        return new InventoryDTO(
            savedInventory.getInventoryId(),
            savedInventory.getFilm().getTitle(),
            savedInventory.getStore().getStoreId(),
            savedInventory.getLastUpdate()
        );
    }
 
 
         

}