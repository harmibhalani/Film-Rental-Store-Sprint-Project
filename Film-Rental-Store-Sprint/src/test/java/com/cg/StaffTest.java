package com.cg;
 
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Arrays;
import java.util.List;
import java.util.Map;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.cg.controller.StaffController;
import com.cg.dto.StaffCreateDTO;
import com.cg.dto.StaffDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.StaffService;
 
class StaffTest {
 
    @InjectMocks
    private StaffController staffController;
 
    @Mock
    private StaffService staffService;
    private StaffDTO staffDTO;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       // staffDTO =new StaffDTO(1, "Joe", "Walter","Jeo@gamil.com","joewalter",);
    }
 
    @Test
    void testCreateStaff() {
        StaffCreateDTO staffCreateDTO = new StaffCreateDTO();
        doNothing().when(staffService).createStaff(staffCreateDTO);
 
        ResponseEntity<String> response = staffController.createStaff(staffCreateDTO);
 
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Record Created Successfully", response.getBody());
    }
 
    @Test
    void testCreateStaff_ResourceNotFoundException() {
        StaffCreateDTO staffCreateDTO = new StaffCreateDTO();
        doThrow(new ResourceNotFoundException("Staff not found"))
            .when(staffService).createStaff(staffCreateDTO);
 
        ResponseEntity<String> response = staffController.createStaff(staffCreateDTO);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Staff not found", response.getBody());
    }
 
    @Test
    void testGetStaffByLastName() {
        List<StaffDTO> mockList = Arrays.asList(new StaffDTO(), new StaffDTO());
        when(staffService.getAllStaffByLastName("Smith")).thenReturn(mockList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.getStaffByLastName("Smith");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
 
    @Test
    void testGetStaffByFirstName() {
        List<StaffDTO> mockList = Arrays.asList(new StaffDTO(), new StaffDTO());
        when(staffService.getAllStaffByFirstName("John")).thenReturn(mockList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.getStaffByFirstName("John");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
 
    @Test
    void testGetStaffByEmail() {
        List<StaffDTO> mockList = Arrays.asList(new StaffDTO());
        when(staffService.getAllStaffByEmail("test@example.com")).thenReturn(mockList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.getStaffByEmail("test@example.com");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testGetStaffByCity() {
        List<StaffDTO> mockList = Arrays.asList(new StaffDTO());
        when(staffService.findByStaff_CityName("New York")).thenReturn(mockList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.getStaffByCity("New York");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testGetStaffByCountry() {
        List<StaffDTO> mockList = Arrays.asList(new StaffDTO(), new StaffDTO());
        when(staffService.findByStaff_Country("USA")).thenReturn(mockList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.getStaffByCountry("USA");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }
 
    @Test
    void testGetStaffByPhone() {
        List<StaffDTO> mockList = Arrays.asList(new StaffDTO());
        when(staffService.findByStaff_Phone("1234567890")).thenReturn(mockList);
 
        ResponseEntity<List<StaffDTO>> response = staffController.getStaffByPhone("1234567890");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }
 
    @Test
    void testUpdateFirstName() {
        StaffDTO updatedStaff = new StaffDTO();
        when(staffService.updateFirstName((short) 1, "John"))
            .thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.updateFirstName((short) 1, "John");
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testUpdateLastName() {
        StaffDTO updatedStaff = new StaffDTO();
        when(staffService.updateLastName((short) 1, "Doe"))
            .thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.updateLastName((short) 1, "Doe");
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testUpdateEmail() {
        StaffDTO updatedStaff = new StaffDTO();
        when(staffService.updateEmail((short) 1, "updated@example.com"))
            .thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.updateEmail((short) 1, "updated@example.com");
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
 
    @Test
    void testAssignStore() {
        Map<String, Object> mockResponse = Map.of("staffId", 1, "storeId", 2);
        when(staffService.assignStore((short) 1, (short) 2))
            .thenReturn(mockResponse);
 
        ResponseEntity<Object> response = staffController.assignStore((short) 1, (short) 2);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }
 
    @Test
    void testAssignStore_ResourceNotFoundException() {
        doThrow(new ResourceNotFoundException("Store not found"))
            .when(staffService).assignStore((short) 1, (short) 2);
 
        ResponseEntity<Object> response = staffController.assignStore((short) 1, (short) 2);
 
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Store not found", response.getBody());
    }
 
    @Test
    void testUpdatePhoneNumber() {
        StaffDTO updatedStaff = new StaffDTO();
        when(staffService.updatePhoneNumber((short) 1, "9876543210"))
            .thenReturn(updatedStaff);
 
        ResponseEntity<StaffDTO> response = staffController.updatePhoneNumber((short) 1, "9876543210");
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
 