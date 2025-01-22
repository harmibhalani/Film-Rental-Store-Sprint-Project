package com.cg.service;
 
import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 
import com.cg.model.Inventory;
 
@Service
public class InventoryServiceImpl implements InventoryService {
 
    @Autowired
    private RestTemplate restTemplate;
 
    // Base URL of the Inventory API
    private static final String INVENTORY_API_BASE_URL = "http://localhost:4311/api/inventory";
 
    // Get Inventories by Film Title
    @Override
    public List<Inventory> getInventoriesByFilmTitle(String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
 
        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Make GET request to fetch inventories for the film title
        ResponseEntity<Inventory[]> response = restTemplate.exchange(
                INVENTORY_API_BASE_URL + "/films/{title}",
                HttpMethod.GET,
                entity,
                Inventory[].class,
                title
        );
 
        // Convert the array of InventoryDTO to List and return
        return Arrays.asList(response.getBody());
    }
 
    // Add Inventory to a Store
    @Override
    public Inventory addInventory(Inventory inventoryDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
 
        // Create HttpEntity with the inventoryDTO as the body
        HttpEntity<Inventory> entity = new HttpEntity<>(inventoryDTO, headers);
 
        // Make POST request to add the inventory
        ResponseEntity<Inventory> response = restTemplate.exchange(
                INVENTORY_API_BASE_URL + "/add",
                HttpMethod.POST,
                entity,
                Inventory.class
        );
 
        // Return the created inventoryDTO
        return response.getBody();
    }
 
	@Override
	public List<Inventory> getAllInventories() {
		// TODO Auto-generated method stub
		return null;
	}
 
 
	
}

