package com.cg.service;
 
import java.util.List;
 
import com.cg.model.Staff;
 
public interface StaffService {
	 List<Staff> searchByFirstName(String firstName) throws Exception;
	    List<Staff> searchByLastName(String lastName) throws Exception;
	  //List<Staff> searchByLastName(String lastName) throws Exception;
	Staff updateStaffByFirstName(Short id, Staff staff);
	Staff updateStaffByLastName(Short id, Staff staff);
}