package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    
    List<Rental> findByCustomerId(Integer customerId); 
}