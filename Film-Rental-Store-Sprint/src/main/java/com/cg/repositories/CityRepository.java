package com.cg.repositories;
 
 
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.cg.model.City;
 
public interface CityRepository extends JpaRepository<City,Long> {
 
 
}