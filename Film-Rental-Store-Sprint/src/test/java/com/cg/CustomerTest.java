package com.cg;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.cg.controller.CustomerController;
import com.cg.dto.CustomerCreateDTO;
import com.cg.dto.CustomerDTO;
 
import com.cg.model.Address;
import com.cg.service.CustomerService;
import java.util.*;
 
class CustomerTest {
 
    @InjectMocks
    private CustomerController customerController;
 
    @Mock
    private CustomerService customerService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testGetCustomersByLastName() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllCustomersByLastName("Smith")).thenReturn(customers);
 
        ResponseEntity<?> response = customerController.getCustomersByLastName("Smith");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetCustomersByFirstName() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllCustomersByFirstName("John")).thenReturn(customers);
 
        ResponseEntity<?> response = customerController.getCustomersByFirstName("John");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetCustomersByEmail() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllCustomersByEmail("test@example.com")).thenReturn(customers);
 
        ResponseEntity<?> response = customerController.getCustomersByEmail("test@example.com");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetCustomersByCity() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllCustomersByCity("New York")).thenReturn(customers);
 
        ResponseEntity<?> response = customerController.getCustomersByCity("New York");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetCustomersByCountry() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllCustomersByCountry("USA")).thenReturn(customers);
 
        ResponseEntity<?> response = customerController.getCustomersByCountry("USA");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetActiveCustomers() {
        List<CustomerDTO> activeCustomers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllActiveCustomers()).thenReturn(activeCustomers);
 
        ResponseEntity<?> response = customerController.getActiveCustomers();
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetInactiveCustomers() {
        List<CustomerDTO> inactiveCustomers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllInactiveCustomers()).thenReturn(inactiveCustomers);
 
        ResponseEntity<?> response = customerController.getInactiveCustomers();
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testGetCustomersByPhone() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO());
        when(customerService.getAllCustomersByPhone("1234567890")).thenReturn(customers);
 
        ResponseEntity<?> response = customerController.getCustomersByPhone("1234567890");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testCreateCustomer() {
        CustomerCreateDTO createDTO = new CustomerCreateDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.createCustomer(createDTO)).thenReturn(customerDTO);
 
        ResponseEntity<CustomerDTO> response = customerController.createCustomer(createDTO);
 
        assertEquals(201, response.getStatusCode().value());
    }
 
    @Test
    void testUpdateCustomerAddress() {
        Address address = new Address();
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomerAddress((short) 1, (short) 1, address)).thenReturn(customerDTO);
 
        ResponseEntity<?> response = customerController.updateCustomerAddress((short) 1, (short) 1, address);
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testUpdateCustomerFirstName() {
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomerFirstName((short) 1, "John")).thenReturn(customerDTO);
 
        ResponseEntity<?> response = customerController.updateCustomerFirstName((short) 1, "John");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testUpdateCustomerLastName() {
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomerLastName((short) 1, "Smith")).thenReturn(customerDTO);
 
        ResponseEntity<?> response = customerController.updateCustomerLastName((short) 1, "Smith");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testUpdateCustomerEmail() {
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomerEmail((short) 1, "new@example.com")).thenReturn(customerDTO);
 
        ResponseEntity<?> response = customerController.updateCustomerEmail((short) 1, "new@example.com");
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testUpdateCustomerStore() {
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomerStore((short) 1, (short) 1)).thenReturn(customerDTO);
 
        ResponseEntity<?> response = customerController.updateCustomerStore((short) 1, (short) 1);
 
        assertEquals(200, response.getStatusCode().value());
    }
 
    @Test
    void testUpdateCustomerPhone() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("phone", "1234567890");
        CustomerDTO customerDTO = new CustomerDTO();
        when(customerService.updateCustomerPhone((short) 1, "1234567890")).thenReturn(customerDTO);
 
        ResponseEntity<?> response = customerController.updateCustomerPhone((short) 1, requestBody);
 
        assertEquals(200, response.getStatusCode().value());
    }
}