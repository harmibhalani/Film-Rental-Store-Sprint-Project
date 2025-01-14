package com.cg.dto;

import java.time.LocalDateTime;

public class InventoryDTO {
    private Integer inventoryId;
    private String filmTitle; // Assuming Film has a title field
    private Short storeId; // Change this to Short if that's how Store ID is represented
    private LocalDateTime lastUpdate;

    // Constructor
    public InventoryDTO(Integer inventoryId, String filmTitle, Short storeId, LocalDateTime lastUpdate) {
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
}
