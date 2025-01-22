package com.cg.model;
 
import java.time.LocalDateTime;
 
public class Inventory {
 
    private Integer inventoryId;
    private String filmTitle;
    private Short storeId;
    private LocalDateTime lastUpdate;
    
    private Integer filmId;
    private Boolean isRented;
    
    
 
    public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
    public Inventory(Integer inventoryId, String filmTitle, Short storeId, LocalDateTime lastUpdate, Boolean isRented, Integer filmId) {
        this.inventoryId = inventoryId;
        this.filmTitle = filmTitle;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
        this.isRented = isRented;
        this.filmId = filmId;
    }
 
	public Inventory(Integer inventoryId, String filmTitle, Short storeId, LocalDateTime lastUpdate) {
        this.inventoryId = inventoryId;
        this.filmTitle = filmTitle;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }
 
    // Getters and Setters
    public Integer getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
 
    public String getFilmTitle() {
        return filmTitle;
    }
 
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }
 
    public Short getStoreId() {
        return storeId;
    }
 
    public void setStoreId(Short storeId) {
        this.storeId = storeId;
    }
 
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
	public Integer getFilmId() {
		return filmId;
	}
	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}
	public Boolean getIsRented() {
		return isRented;
	}
	public void setIsRented(Boolean isRented) {
		this.isRented = isRented;
	}
    
    
}
 
