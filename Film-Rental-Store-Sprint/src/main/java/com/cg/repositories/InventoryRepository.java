package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    
    List<Inventory> findByFilm_Title(String title); 
}