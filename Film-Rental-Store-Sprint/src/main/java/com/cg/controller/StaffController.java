package com.cg.controller;
 
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.dto.StaffCreateDTO;
import com.cg.dto.StaffDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.StaffService;
 
@RestController
@RequestMapping("/api")
public class StaffController {
 
    @Autowired
    private StaffService staffService;
 
    //Add new Staff object to DB
    @PostMapping("/staff/post")
    public ResponseEntity<String> createStaff(@Validated @RequestBody StaffCreateDTO staffCreateDTO) {
        try {
            staffService.createStaff(staffCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    //Search Staff by last name
    @GetMapping("/staff/lastname/{ln}")
    public ResponseEntity<List<StaffDTO>> getStaffByLastName(@PathVariable String ln) {
        List<StaffDTO> staffList = staffService.getAllStaffByLastName(ln);    
        return ResponseEntity.ok(staffList);
    }
    //Search Staff by first name
    @GetMapping("/staff/firstname/{fn}")
    public ResponseEntity<List<StaffDTO>> getStaffByFirstName(@PathVariable String fn) {
        List<StaffDTO> staffList = staffService.getAllStaffByFirstName(fn);
        return ResponseEntity.ok(staffList);
    }
 
    //Search Staff by email
    @GetMapping("/staff/email/{email}")
    public ResponseEntity<List<StaffDTO>> getStaffByEmail(@PathVariable String email) {
        List<StaffDTO> staffList = staffService.getAllStaffByEmail(email);
        return ResponseEntity.ok(staffList);
    }
    //Search Staff by City
    @GetMapping("/staff/city/{city}")
    public ResponseEntity<List<StaffDTO>> getStaffByCity(@PathVariable String city) {
        List<StaffDTO> staffList = staffService.findByStaff_CityName(city);
        return ResponseEntity.ok(staffList);
    }
    //Search Customers by Country
	@GetMapping("/staff/country/{country}")
	public ResponseEntity<List<StaffDTO>> getStaffByCountry(@PathVariable String country) {
	    List<StaffDTO> staffList = staffService.findByStaff_Country(country);
	    return ResponseEntity.ok(staffList);
	}
 
	//Search Staff by phone number
	@GetMapping("/staff/phone/{phone}")
	public ResponseEntity<List<StaffDTO>> getStaffByPhone(@PathVariable String phone) {
	    List<StaffDTO> staffList = staffService.findByStaff_Phone(phone);
	    return ResponseEntity.ok(staffList);
	}
//	Update first name of Staff
	@PutMapping("/staff/update/fn/{id}")
	public ResponseEntity<StaffDTO> updateFirstName(@PathVariable Short id,
	                                                @RequestBody StaffDTO staffDto) {
	    String firstName = staffDto.getFirstName();
	    if (firstName == null || firstName.isBlank()) {
	        throw new IllegalArgumentException("First name is mandatory");
	    }
	    StaffDTO updatedStaff = staffService.updateFirstName(id, firstName);
	    return ResponseEntity.ok(updatedStaff);
	}
 
	//Update last name of Staff
		@PutMapping("/staff/update/ln/{id}")
		public ResponseEntity<StaffDTO> updateLastName(@PathVariable Short id,
		                                                @RequestBody StaffDTO staffDto) {
		    String lastName = staffDto.getLastName();
		    if (lastName == null || lastName.isBlank()) {
		        throw new IllegalArgumentException("Last name is mandatory");
		    }
		    StaffDTO updatedStaff = staffService.updateLastName(id, lastName);
		    return ResponseEntity.ok(updatedStaff);
		}
 
	//Update email of Staff
	@PutMapping("/staff/update/email/{id}")
	public ResponseEntity<StaffDTO> updateEmail(@PathVariable Short id, @RequestBody String email)  {
		StaffDTO updatedstaff=staffService.updateEmail(id,email);
	    return ResponseEntity.ok(updatedstaff);
	}
 
//Assign Store to a Staff
	@PutMapping("/staff/update/store/{id}")
	public ResponseEntity<Object> assignStore(@PathVariable Short id, @RequestBody Short storeid) {
	    try {    
	        Map<String, Object> response = staffService.assignStore(id, storeid);
	        return ResponseEntity.ok(response);
	    } catch (ResourceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	}
	//Update phone number of a Staff
	@PutMapping("/staff/update/phone/{id}")
	public ResponseEntity<StaffDTO> updatePhoneNumber(@PathVariable Short id, @RequestBody String phone)  {
		StaffDTO updatedstaff=staffService.updatePhoneNumber(id,phone);
	    return ResponseEntity.ok(updatedstaff);
	}
}