package com.cg.model;
 
import java.time.LocalDateTime;
import jakarta.persistence.*;
 
@Entity
@Table(name = "inventory")
public class Inventory {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
 
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
 
    // Default constructor
    public Inventory() {
        this.lastUpdate = LocalDateTime.now();
    }
 
    // Getters and Setters
    public Integer getInventoryId() {
        return inventoryId;
    }
 
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
 
    public Film getFilm() {
        return film;
    }
 
    public void setFilm(Film film) {
        this.film = film;
    }
 
    public Store getStore() {
        return store;
    }
 
    public void setStore(Store store) {
        this.store = store;
    }
 
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}