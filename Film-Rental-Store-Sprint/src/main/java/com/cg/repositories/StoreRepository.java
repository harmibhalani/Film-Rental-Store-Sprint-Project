package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dto.StoreDTO;
import com.cg.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Short> {
	
	 @Query("SELECT NEW com.cg.dto.StoreDTO(s.storeId, s.manager.firstName, " +
	           "a.address, c.city, c.country.country, a.phone) " +
	           "FROM Store s " +
	           "JOIN s.address a " +
	           "JOIN a.city c " +
	           "WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :cityName, '%'))")
	    List<StoreDTO> findByCityName(@Param("cityName") String cityName);
}