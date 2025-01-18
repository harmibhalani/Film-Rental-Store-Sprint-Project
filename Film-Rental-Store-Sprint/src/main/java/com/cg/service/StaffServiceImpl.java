package com.cg.service;
 
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
 
import com.cg.dto.StaffCreateDTO;
import com.cg.dto.StaffDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Address;
 
import com.cg.model.Staff;
import com.cg.model.Store;
import com.cg.repositories.AddressRepository;
 
import com.cg.repositories.StaffRepository;
import com.cg.repositories.StoreRepository;
 
import jakarta.transaction.Transactional;
 
@Service
public class StaffServiceImpl implements StaffService {
 
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StoreRepository storeRepository;
//    
    @Autowired
    private AddressRepository addressRepository;

 
//Add new Staff object to DB
    @Override
    @Transactional
    public void createStaff(StaffCreateDTO staffCreateDTO) {
        Address address = addressRepository.findById(staffCreateDTO.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + staffCreateDTO.getAddressId()));
        Store store = storeRepository.findById(staffCreateDTO.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + staffCreateDTO.getStoreId()));
 
        Staff newStaff = new Staff();
        newStaff.setFirstName(staffCreateDTO.getFirstName());
        newStaff.setLastName(staffCreateDTO.getLastName());
        newStaff.setAddress(address);
        newStaff.setEmail(staffCreateDTO.getEmail());
        newStaff.setStore(store);
        newStaff.setUsername(staffCreateDTO.getUsername());
        newStaff.setPassword(staffCreateDTO.getPassword()); 
        newStaff.setLastUpdate(LocalDateTime.now());
 
        staffRepository.save(newStaff);
    }
 

   // Search Staff by last name
    @Override
    public List<StaffDTO> getAllStaffByLastName(String lastName) {
        List<Staff> staffList = staffRepository.findByLastName(lastName);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with last name: " + lastName);
        }
        return convertToDTO(staffList);
    }
   // Search Staff by first name
    @Override
    public List<StaffDTO> getAllStaffByFirstName(String firstName) {
        List<Staff> staffList = staffRepository.findByFirstName(firstName);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with first name: " + firstName);
        }
        return convertToDTO(staffList);
    }
    //Search Staff by email
    @Override
    public List<StaffDTO> getAllStaffByEmail(String email) {
        List<Staff> staffList = staffRepository.findByEmail(email);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with email: " + email);
        }
        return convertToDTO(staffList);
    }
   //Search Staff by City
    @Override
    public List<StaffDTO> findByStaff_CityName(String city) {
        List<Staff> staffList = staffRepository.findByAddress_City_City(city);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found in city: " + city);
        }
        return convertToDTO(staffList);
    }
 
    //Search Customers by Country
    @Override
    public List<StaffDTO> findByStaff_Country(String country) {
        List<Staff> staffList = staffRepository.findByAddress_City_Country_Country(country);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found in country: " + country);
        }
        return convertToDTO(staffList);
    }
 
    //Search Staff by phone number
    @Override
    public List<StaffDTO> findByStaff_Phone(String phone) {
        List<Staff> staffList = staffRepository.findByAddress_Phone(phone);
        if (staffList.isEmpty()) {
            throw new ResourceNotFoundException("No staff found with phone number: " + phone);
        }
        return convertToDTO(staffList);
    }
    //Update first name of Staff
    @Override
    public StaffDTO updateFirstName(Short staffId, String firstName)  {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
        staff.setFirstName(firstName);
        staff = staffRepository.save(staff);
 
        return new StaffDTO(
                staff.getStaffId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getEmail(),
                staff.getUsername(),
                staff.getActive()
        );
    }
 
    //Update last name of Staff
    @Override
    public StaffDTO updateLastName(Short staffId, String lastName) {
        // Find the staff member by ID, throwing an exception if not found
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
        // Update the last name
        staff.setLastName(lastName);
        // Save the updated staff entity and convert to DTO
        return saveAndConvertToDTO(staff);
    }
 
//    Update email of Staff
    @Override
    public StaffDTO updateEmail(Short staffId, String email) {
        // Find the staff member by ID, throwing an exception if not found
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
        // Update the email address
        staff.setEmail(email);
        // Save the updated staff entity and convert to DTO
        return saveAndConvertToDTO(staff);
    }
 
    //Assign Store to a Staff
    @Override
    public Map<String, Object> assignStore(Short staffId, Short storeId) throws ResourceNotFoundException {
        Staff staff = findStaffById(staffId);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + storeId));
        staff.setStore(store);
        Staff updatedStaff = staffRepository.save(staff);
        StaffDTO staffDTO = new StaffDTO(
            updatedStaff.getStaffId(),
            updatedStaff.getFirstName(),
            updatedStaff.getLastName(),
            updatedStaff.getEmail(),
            updatedStaff.getUsername(),
            updatedStaff.getActive()
        );
        Map<String, Object> response = new HashMap<>();
        response.put("staffDetails", staffDTO);
        response.put("storeId", store.getStoreId());
        return response;
    }
//Update phone number of a Staff
    @Override
    public StaffDTO updatePhoneNumber(Short staffId, String phone) {
        Staff staff = findStaffById(staffId);
 
        // Assuming Address is a separate entity and has a setter for phone number.
        if (staff.getAddress() != null) {
            staff.getAddress().setPhone(phone); 
            // Save and convert to DTO after updating phone number.
            return saveAndConvertToDTO(staff);
        } else {
            throw new ResourceNotFoundException("Address not found for the given Staff ID: " + staffId);
        }
    }
 
    // Helper method to find Staff by ID with exception handling.
    private Staff findStaffById(Short staffId) throws ResourceNotFoundException {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
    }
 
    // Helper method to save Staff and convert to DTO
    private StaffDTO saveAndConvertToDTO(Staff staff) {
        Staff updatedStaff = staffRepository.save(staff);
        return new StaffDTO(
            updatedStaff.getStaffId(),
            updatedStaff.getFirstName(),
            updatedStaff.getLastName(),
            updatedStaff.getEmail(),
            updatedStaff.getUsername(),
            updatedStaff.getActive()
        );
    }
 
    private List<StaffDTO> convertToDTO(List<Staff> staffList) {
        return staffList.stream()
            .map(staff -> new StaffDTO(
                staff.getStaffId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getEmail(),
                staff.getUsername(),
                staff.getActive()))
            .collect(Collectors.toList());
    }
 
}