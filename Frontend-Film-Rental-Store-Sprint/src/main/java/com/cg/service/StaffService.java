package com.cg.service;

import java.util.List;

import com.cg.model.Staff;

public interface StaffService {
	
	List<Staff> getStaffByFirstName(String firstName) throws Exception;

	Staff updateStaffByFirstName(Integer staffId, Staff staff);
}
