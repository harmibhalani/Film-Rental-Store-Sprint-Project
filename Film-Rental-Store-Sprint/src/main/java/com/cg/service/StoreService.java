package com.cg.service;

import java.util.List;

import com.cg.dto.StoreDTO;
import com.cg.model.Store;

public interface StoreService {
	
	List<StoreDTO> findStoresByCity(String cityName);

	
}
