package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.StaffDTO;
import com.cg.model.Staff;
import com.cg.repositories.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<StaffDTO> getAllStaffByLastName(String lastName) {
        List<Staff> staffList = staffRepository.findByLastName(lastName);
        
        // Convert List<Staff> to List<StaffDTO>
        return staffList.stream()
            .map(staff -> new StaffDTO(staff.getStaffId(), staff.getFirstName(), 
                                        staff.getLastName(), staff.getEmail(), 
                                        staff.getUsername(), staff.getActive()))
            .collect(Collectors.toList());
    }
}