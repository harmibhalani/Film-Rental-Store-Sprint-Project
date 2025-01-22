package com.cg.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cg.model.Inventory;
import com.cg.service.InventoryService;

@Controller
@RequestMapping("/homePage/dashboard/inventoryManagement")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/view")
	public String showInventoryPage(Model model) {
		List<Inventory> inventories = inventoryService.getAllInventories();
		model.addAttribute("inventories", inventories);
		return "inventoryManagement";
	}

	@GetMapping("/films/{title}")
	@ResponseBody
	public ResponseEntity<List<Inventory>> getInventoriesByFilmTitle(@PathVariable String title) {
		try {
			List<Inventory> inventories = inventoryService.getInventoriesByFilmTitle(title);
			return ResponseEntity.ok(inventories);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<?> addInventory(@RequestBody Inventory inventoryDTO) {
		try {
			if (inventoryDTO.getFilmId() == null || inventoryDTO.getStoreId() == null) {
				return ResponseEntity.badRequest().body("Film ID and Store ID are required");
			}
			inventoryDTO.setLastUpdate(LocalDateTime.now());
			inventoryDTO.setIsRented(false);
			Inventory createdInventory = inventoryService.addInventory(inventoryDTO);
			if (createdInventory == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create inventory");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding inventory: " + e.getMessage());
		}
	}
}