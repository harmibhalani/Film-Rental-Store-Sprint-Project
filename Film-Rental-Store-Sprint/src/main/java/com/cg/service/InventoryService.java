package com.cg.service;
 
import java.util.List;
 
import com.cg.dto.InventoryDTO;
 
public interface InventoryService {
	 // Display inventory(count) of all Films in all Stores
    List<InventoryDTO> getInventoriesByFilmTitle(String title);
    //Display inventory of all Films by a Store
	List<InventoryDTO> getInventoriesByStoreId(Short storeId);
	//Display inventory(count) of a Film in all Stores
	List<InventoryDTO> getInventoriesByFilmId(Integer filmId);
	//Display inventory(count) of a Film in a Store
	List<InventoryDTO> getInventoriesByFilmIdAndStoreId(Integer filmId, Short storeId);
 
	//Add one more Film to a Store
    InventoryDTO addInventory(InventoryDTO inventoryDTO);

}