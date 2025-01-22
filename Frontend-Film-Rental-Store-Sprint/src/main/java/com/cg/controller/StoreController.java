package com.cg.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 
import com.cg.model.Store;
 
import com.cg.service.StoreService;
 
 
@Controller
@RequestMapping("/homePage/dashboard/storeManagement")
public class StoreController {
 
	 @Autowired
	    private StoreService storeService;
	 @GetMapping("/search-city")
	    @ResponseBody
	    public ResponseEntity<List<Store>> searchFilm(@RequestParam("city") String city) {
	        try {
	            List<Store> store = storeService.getStoreByCityName(city);
	            return ResponseEntity.ok(store);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }

 
	 @PutMapping("/update/{id}")  
	 @ResponseBody
	 public ResponseEntity<?> updatePhone(
	     @PathVariable("id") Short storeId,
     @RequestBody Store storeDTO) {
	     try {
	         Store updatedStore = storeService.updateStorePhone(storeId, storeDTO);
	         return ResponseEntity.ok(updatedStore);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	             .body("Error updating Phone: " + e.getMessage());
	     }
	 }

}