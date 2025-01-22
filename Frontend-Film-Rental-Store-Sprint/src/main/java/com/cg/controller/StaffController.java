package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cg.model.Actor;
import com.cg.model.Staff;
import com.cg.service.StaffService;

@Controller
@RequestMapping("/homePage/dashboard/staffManagement")
public class StaffController {

	@Autowired
	private StaffService staffService;

	// GET request to search staff by first name
	@GetMapping("/search-staff")
	@ResponseBody
	public ResponseEntity<List<Staff>> searchStaff(@RequestParam("firstName") String firstName) {
		try {
			List<Staff> staffs = staffService.getStaffByFirstName(firstName);
			return ResponseEntity.ok(staffs);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// PUT request to update an staff's first name by staffId
	@PutMapping("/update/fn/{id}")
	public ResponseEntity<?> updateStaffFirstName(@PathVariable("id") Integer staffId, @RequestBody Staff staffDTO) {
		try {
			Staff updatedStaff = staffService.updateStaffByFirstName(staffId, staffDTO);
			return ResponseEntity.ok(updatedStaff);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating staff: " + e.getMessage());
		}
	}
}
