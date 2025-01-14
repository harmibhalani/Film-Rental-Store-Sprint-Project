package com.cg.service;

import java.util.List;

import com.cg.dto.StaffDTO;
import com.cg.model.Staff;

public interface StaffService {
    
	 List<StaffDTO> getAllStaffByLastName(String lastName);
}
