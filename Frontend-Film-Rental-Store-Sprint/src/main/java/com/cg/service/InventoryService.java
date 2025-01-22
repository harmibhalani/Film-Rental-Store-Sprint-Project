package com.cg.service;
 
import java.util.List;
 
import com.cg.model.Inventory;
 
public interface InventoryService {  
List<Inventory> getInventoriesByFilmTitle(String title);
Inventory addInventory(Inventory inventoryDTO);
List<Inventory> getAllInventories();
 
 
}