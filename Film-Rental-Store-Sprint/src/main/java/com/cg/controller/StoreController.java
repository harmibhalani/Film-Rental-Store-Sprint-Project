package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.StoreDTO;
import com.cg.model.Store;
import com.cg.service.StoreService;

@RestController
@RequestMapping("/api")
public class StoreController {
    
    @Autowired
    private StoreService storeService;
    
    @GetMapping("/store/city/{cityName}")
    public ResponseEntity<?> getStoresByCity(@PathVariable String cityName) {
        try {
            List<StoreDTO> stores = storeService.findStoresByCity(cityName);
            if (stores.isEmpty()) {
                return new ResponseEntity<>(
                    "No stores found in city: " + cityName, 
                    HttpStatus.NOT_FOUND
                );
            }
            return ResponseEntity.ok(stores);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                e.getMessage(), 
                HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                "An error occurred while fetching stores: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}