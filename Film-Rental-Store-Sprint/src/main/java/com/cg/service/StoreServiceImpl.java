package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.StoreDTO;
import com.cg.model.Store;
import com.cg.repositories.StoreRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
    private StoreRepository storeRepository;
    
	@Override
    @Transactional(readOnly = true)
    public List<StoreDTO> findStoresByCity(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }
        return storeRepository.findByCityName(cityName.trim());
    }
}
