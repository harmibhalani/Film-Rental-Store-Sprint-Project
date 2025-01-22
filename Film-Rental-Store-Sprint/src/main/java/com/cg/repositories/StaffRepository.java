package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Short> {
	// Search Staff by last name
	List<Staff> findByLastName(String lastName);

	// Search Staff by first name
	List<Staff> findByFirstName(String firstName);

	// Search Staff by email
	List<Staff> findByEmail(String email);

	// Search Staff by City
	List<Staff> findByAddress_City_City(String city);

	// Search Customers by Country
	List<Staff> findByAddress_City_Country_Country(String country);

	// Search Staff by phone number
	List<Staff> findByAddress_Phone(String phone);

	// Assign Store to a Staff
	List<Staff> findByStore_StoreId(Short storeId);

}