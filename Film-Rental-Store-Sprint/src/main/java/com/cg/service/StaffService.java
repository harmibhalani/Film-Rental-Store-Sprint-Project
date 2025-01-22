package com.cg.service;

import java.util.List;
import java.util.Map;

import com.cg.dto.AddressDTO;
import com.cg.dto.StaffCreateDTO;
import com.cg.dto.StaffDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Address;
import com.cg.model.Staff;

public interface StaffService {
	// Add new Staff object to DB
	void createStaff(StaffCreateDTO staffCreateDTO);

	// Search Staff by last name
	List<StaffDTO> getAllStaffByLastName(String lastName);

	// Search Staff by first name
	List<StaffDTO> getAllStaffByFirstName(String firstName);

	// Search Staff by email
	List<StaffDTO> getAllStaffByEmail(String email);

	// Search Staff by City
	List<StaffDTO> findByStaff_CityName(String city);

	// Search Customers by Country
	List<StaffDTO> findByStaff_Country(String country);

	// Search Staff by phone number
	List<StaffDTO> findByStaff_Phone(String phone);

	// Update first name of Staff
	StaffDTO updateFirstName(Short staffId, String firstName);

	// Update last name of Staff
	StaffDTO updateLastName(Short staffId, String lastName);

	// Update email of Staff
	StaffDTO updateEmail(Short staffId, String email);

	// StaffDTO assignStore(Short staffId, Short storeId) ;
	Map<String, Object> assignStore(Short staffId, Short storeId);

	// Update phone number of a Staff
	StaffDTO updatePhoneNumber(Short staffId, String phone);

}