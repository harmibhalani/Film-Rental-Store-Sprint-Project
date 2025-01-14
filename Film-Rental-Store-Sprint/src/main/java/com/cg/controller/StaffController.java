package com.cg.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.StaffDTO;
import com.cg.model.Staff;
import com.cg.service.StaffService;

@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/staff/lastname/{ln}")
    public ResponseEntity<List<StaffDTO>> getStaffByLastName(@PathVariable String ln) {
        List<StaffDTO> staffList = staffService.getAllStaffByLastName(ln);
        
        // Convert List<Staff> to List<StaffDTO>
        List<StaffDTO> staffDTOs = staffList.stream()
            .map(staff -> new StaffDTO(staff.getStaffId(), staff.getFirstName(), staff.getLastName(), 
                                        staff.getEmail(), staff.getUsername(), staff.getActive()))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(staffDTOs);
    }
}