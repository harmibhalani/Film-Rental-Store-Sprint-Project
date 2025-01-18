package com.cg.dto;
 
import java.time.LocalDateTime;
 
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
 
public class InventoryDTO {

 
  
	  @NotNull(message = "Inventory ID cannot be null.")
	    @Min(value = 1, message = "Inventory ID must be a positive number.")
	    private Integer inventoryId;

	  @NotEmpty(message = "Film title cannot be empty.")
	    @Size(min = 1, max = 255, message = "Film title must be between 1 and 255 characters.")
	    private String filmTitle;

 
    
	  @NotNull(message = "Store ID cannot be null.")
	    @Min(value = 1, message = "Store ID must be a positive number.")
	  @Max(value = 2, message = "Store ID cannot be greater than 2.")
	    private Short storeId;
	  @NotNull(message = "Last update cannot be null.")
	    @PastOrPresent(message = "Last update must be in the past or present.")
	    private LocalDateTime lastUpdate;
 
	  

    public InventoryDTO(Integer inventoryId, String filmTitle, Short storeId, LocalDateTime lastUpdate) {
        this.inventoryId = inventoryId;
        this.filmTitle = filmTitle;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }
 
   
    public Integer getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(Integer inventoryId) {
        if (inventoryId == null || inventoryId < 1) {
            throw new IllegalArgumentException("Inventory ID must be a positive number.");
        }
        this.inventoryId = inventoryId;
    }
    public String getFilmTitle() {
        return filmTitle;
    }
 
    public void setFilmTitle(String filmTitle) {
        if (filmTitle == null || filmTitle.isEmpty()) {
            throw new IllegalArgumentException("Film title cannot be empty.");
        }
        this.filmTitle = filmTitle;
    }
    public Short getStoreId() {
        return storeId;
    }
 
    public void setStoreId(Short storeId) {
        if (storeId == null || storeId < 1 || storeId > 2) {  // Validation in setter (you could also rely on @Max)
            throw new IllegalArgumentException("Store ID must be a positive number and cannot be greater than 2.");
        }
        this.storeId = storeId;
    }
 
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(LocalDateTime lastUpdate) {
        if (lastUpdate == null || lastUpdate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Last update must be in the past or present.");
        }
        this.lastUpdate = lastUpdate;
    }
}