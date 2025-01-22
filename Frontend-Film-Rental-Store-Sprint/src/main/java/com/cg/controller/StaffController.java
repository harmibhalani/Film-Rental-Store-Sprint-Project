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
    public ResponseEntity<?> searchStaff(
        @RequestParam String searchType,
        @RequestParam String searchTerm) {
        try {
            List<Staff> results;
            switch (searchType) {
                case "firstname":
                    results = staffService.searchByFirstName(searchTerm);
                    break;
                case "lastname":
                    results = staffService.searchByLastName(searchTerm);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid search type");
            }
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error performing search: " + e.getMessage());
        }
    }
 
	// PUT request to update an staff's first name by staffId
	 @PutMapping("/update/fn/{Id}")  // Changed from /fn/{id}
	 @ResponseBody
	 public ResponseEntity<?> updateFirstName(
	     @PathVariable("Id") Short staffId,
     @RequestBody Staff staffDTO) {
	     try {
	         Staff updatedStaff = staffService.updateStaffByFirstName(staffId, staffDTO);
	         return ResponseEntity.ok(updatedStaff);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	             .body("Error updating firstname: " + e.getMessage());
	     }
	 }

	 @PutMapping("/update/ln/{Id}")  // Changed from /fn/{id}
	 @ResponseBody
	 public ResponseEntity<?> updateLastName(
	     @PathVariable("Id") Short staffId,
     @RequestBody Staff staffDTO) {
	     try {
	         Staff updatedStaff = staffService.updateStaffByLastName(staffId, staffDTO);
	         return ResponseEntity.ok(updatedStaff);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	             .body("Error updating lastname: " + e.getMessage());
	     }
	 }
}