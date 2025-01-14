package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.InventoryDTO;
import com.cg.service.InventoryService;

@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory/films/{title}")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByFilmTitle(@PathVariable String title) {
        List<InventoryDTO> inventories = inventoryService.getInventoriesByFilmTitle(title);
        return ResponseEntity.ok(inventories);
    }
}