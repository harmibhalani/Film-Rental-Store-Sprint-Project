package com.cg.service;

import java.util.List;

import com.cg.dto.InventoryDTO;

public interface InventoryService {
    
    List<InventoryDTO> getInventoriesByFilmTitle(String title);
}