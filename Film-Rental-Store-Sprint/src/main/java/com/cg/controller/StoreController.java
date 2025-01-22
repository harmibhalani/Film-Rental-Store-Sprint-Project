package com.cg.controller;
 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
import com.cg.dto.*;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.StoreService;
 
@RestController
@RequestMapping("/api")
public class StoreController {
 
    @Autowired
    private StoreService storeService;
 
    // Add new Store object to DB
    @PostMapping("/store/post")
    public ResponseEntity<String> addStore(@Validated @RequestBody StoreCreateDTO storeCreateDTO) {
        storeService.addStore(storeCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
 
    // Search Store by City
    @GetMapping("/store/city/{cityName}")
    public ResponseEntity<List<StoreDTO>> getStoresByCity(@PathVariable String cityName) {
        List<StoreDTO> stores = storeService.findStoresByCity(cityName);
        return ResponseEntity.ok(stores);
    }
 
    // Search Store by Country
    @GetMapping("/store/country/{country}")
    public ResponseEntity<List<StoreDTO>> getStoresByCountry(@PathVariable String country) {
        List<StoreDTO> stores = storeService.findStoresByCountry(country);
        return ResponseEntity.ok(stores);
    }
 
    // Search Store by phone number
    @GetMapping("/store/phone/{phone}")
    public ResponseEntity<StoreDTO> getStoreByPhone(@PathVariable String phone) {
        StoreDTO store = storeService.findStoreByPhone(phone);
        return ResponseEntity.ok(store);
    }
 
    // Update phone number of a Store
    @PutMapping("/store/update/{storeId}")
    public ResponseEntity<StoreDTO> updatePhone(@PathVariable Short storeId, @RequestBody Map<String, String> requestBody) {
        String phone = requestBody.get("phone");
        
        if (phone == null || phone.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return bad request if phone is missing
        }
 
        StoreDTO updatedStore = storeService.updateStorePhone(storeId, phone);
        return ResponseEntity.ok(updatedStore); // Return the updated store details
    }
 
    // Assign manager to a Store
    @PutMapping("/store/{storeId}/manager/{managerStaffId}")
    public ResponseEntity<StoreDTO> assignManagerToStore(
            @PathVariable Short storeId,
            @PathVariable Short managerStaffId) {
        StoreDTO updatedStore = storeService.assignManagerToStore(storeId, managerStaffId);
        return ResponseEntity.ok(updatedStore);
    }
 
    // Display all Staff of a Store
    @GetMapping("/store/staff/{storeId}")
    public ResponseEntity<List<StaffDTO>> getStoreStaff(@PathVariable Short storeId) {
        List<StaffDTO> staff = storeService.findStaffByStoreId(storeId);
        return ResponseEntity.ok(staff);
    }
 
    // Display all Customers of a Store
    @GetMapping("/store/customer/{storeId}")
    public ResponseEntity<List<CustomerDTO>> getStoreCustomers(@PathVariable Short storeId) {
        List<CustomerDTO> customers = storeService.findCustomersByStoreId(storeId);
        return ResponseEntity.ok(customers);
    }
 
    // Display Manager Details of a Store
    @GetMapping("/store/manager/{storeId}")
    public ResponseEntity<StaffDTO> getManagerDetails(@PathVariable Short storeId) {
        StaffDTO manager = storeService.findManagerByStoreId(storeId);
        return ResponseEntity.ok(manager);
    }
 
    // Display Manager details and Store details of all stores
    @GetMapping("/store/managers")
    public ResponseEntity<List<ManagerStoreDTO>> getAllManagers() {
        List<ManagerStoreDTO> managers = storeService.getAllManagersDetails();
        return ResponseEntity.ok(managers);
    }
 // Display all list of Stores
    @GetMapping("/store/all")
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<StoreDTO> stores = storeService.findAllStores();
        return ResponseEntity.ok(stores);
    }
}