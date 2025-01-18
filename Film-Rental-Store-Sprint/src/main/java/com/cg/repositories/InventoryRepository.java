package com.cg.repositories;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.cg.model.Inventory;
 
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    // Display inventory(count) of all Films in all Stores
    List<Inventory> findByFilm_Title(String title) ; 
    //Display inventory of all Films by a Store
    List<Inventory> findByStore_StoreId(Short storeId);
    //Display inventory(count) of a Film in all Stores
    List<Inventory> findByFilm_FilmId(Integer filmId);
    //Display inventory(count) of a Film in a Store
    List<Inventory> findByFilm_FilmIdAndStore_StoreId(Integer filmId, Short storeId);
}