package com.cg;
 
import com.cg.controller.StoreController;
import com.cg.dto.*;
import com.cg.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.ArrayList;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class StoreTest {
 
    @InjectMocks
    private StoreController storeController;
 
    @Mock
    private StoreService storeService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
 
    @Test
    void testGetStoresByCity() {
        String cityName = "New York";
        List<StoreDTO> stores = new ArrayList<>();
        stores.add(new StoreDTO());
        when(storeService.findStoresByCity(cityName)).thenReturn(stores);
 
        ResponseEntity<List<StoreDTO>> response = storeController.getStoresByCity(cityName);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testGetStoresByCountry() {
        String country = "USA";
        List<StoreDTO> stores = new ArrayList<>();
        stores.add(new StoreDTO());
        when(storeService.findStoresByCountry(country)).thenReturn(stores);
 
        ResponseEntity<List<StoreDTO>> response = storeController.getStoresByCountry(country);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testGetStoreByPhone() {
        String phone = "1234567890";
        StoreDTO store = new StoreDTO();
        when(storeService.findStoreByPhone(phone)).thenReturn(store);
 
        ResponseEntity<StoreDTO> response = storeController.getStoreByPhone(phone);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testUpdatePhone() {
        Short storeId = 1;
        String phone = "1234567890";
        doNothing().when(storeService).updateStorePhone(storeId, phone);
 
        ResponseEntity<String> response = storeController.updatePhone(storeId, phone);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals("Phone updated successfully", response.getBody());
    }
 
    @Test
    void testAssignManagerToStore() {
        Short storeId = 1;
        Short managerStaffId = 1;
        StoreDTO storeDTO = new StoreDTO();
        when(storeService.assignManagerToStore(storeId, managerStaffId)).thenReturn(storeDTO);
 
        ResponseEntity<StoreDTO> response = storeController.assignManagerToStore(storeId, managerStaffId);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testGetStoreStaff() {
        Short storeId = 1;
        List<StaffDTO> staffList = new ArrayList<>();
        staffList.add(new StaffDTO());
        when(storeService.findStaffByStoreId(storeId)).thenReturn(staffList);
 
        ResponseEntity<List<StaffDTO>> response = storeController.getStoreStaff(storeId);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testGetStoreCustomers() {
        Short storeId = 1;
        List<CustomerDTO> customerList = new ArrayList<>();
        customerList.add(new CustomerDTO());
        when(storeService.findCustomersByStoreId(storeId)).thenReturn(customerList);
 
        ResponseEntity<List<CustomerDTO>> response = storeController.getStoreCustomers(storeId);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testGetManagerDetails() {
        Short storeId = 1;
        StaffDTO manager = new StaffDTO();
        when(storeService.findManagerByStoreId(storeId)).thenReturn(manager);
 
        ResponseEntity<StaffDTO> response = storeController.getManagerDetails(storeId);
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testGetAllManagers() {
        List<ManagerStoreDTO> managers = new ArrayList<>();
        managers.add(new ManagerStoreDTO());
        when(storeService.getAllManagersDetails()).thenReturn(managers);
 
        ResponseEntity<List<ManagerStoreDTO>> response = storeController.getAllManagers();
 
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
}