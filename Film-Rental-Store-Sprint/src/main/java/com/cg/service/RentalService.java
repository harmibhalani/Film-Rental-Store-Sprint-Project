package com.cg.service;

import java.util.List;

import com.cg.dto.RentalDTO;

public interface RentalService {
	
	List<RentalDTO> getRentalsByCustomerId(Integer customerId);
	
}
