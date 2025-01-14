package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.InventoryDTO;
import com.cg.model.Inventory;
import com.cg.repositories.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

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
}