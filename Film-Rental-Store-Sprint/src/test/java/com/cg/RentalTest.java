package com.cg;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import com.cg.controller.RentalController;
import com.cg.dto.RentalDTO;
import com.cg.service.RentalService;
 
public class RentalTest {
 
    @InjectMocks
    private RentalController rentalController;
 
    @Mock
    private RentalService rentalService;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rentalController).build();
    }
 
    // Rent a  Film
    @Test
    public void testAddRental_Success() throws Exception {
        // Create a valid RentalDTO
        RentalDTO rentalDTO = new RentalDTO(1, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 1, (short) 1, (short) 1);
 
        // Mock the service layer
        when(rentalService.addRental(any(RentalDTO.class))).thenReturn(rentalDTO);
 
        // Perform the POST request and validate the response
        mockMvc.perform(post("/api/rental/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"rentalId\":1,\"rentalDate\":\"2025-01-17T13:00:00\",\"returnDate\":\"2025-01-20T13:00:00\",\"inventoryId\":1,\"staffId\":1,\"customerId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rentalId").value(1))
                .andExpect(jsonPath("$.inventoryId").value(1));
        // Verify that the service method was called
        verify(rentalService).addRental(any(RentalDTO.class));
    }
    // Update return date
    @Test
    public void testUpdateReturnDate_Success() throws Exception {
        // Create a valid future return date
        LocalDateTime futureReturnDate = LocalDateTime.now().plusDays(5);
        String returnDateString = futureReturnDate.toString(); // Convert to string for JSON
 
        // Create a RentalDTO to be returned after update
        RentalDTO updatedRentalDTO = new RentalDTO(1, LocalDateTime.now(), futureReturnDate, 1, (short) 1, (short) 1);
 
        // Mock the service layer
        when(rentalService.updateReturnDate(eq(1), any(LocalDateTime.class))).thenReturn(updatedRentalDTO);
 
        // Perform the POST request and validate the response
        mockMvc.perform(post("/api/rental/update/returndate/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"returnDate\":\"" + returnDateString + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rentalId").value(1));        
        // Verify that the service method was called with correct parameters
        verify(rentalService).updateReturnDate(eq(1), any(LocalDateTime.class));
    }
// Display all Films rented to a customer
    @Test
    public void testGetRentalsByCustomer_Success() throws Exception {
        Short customerId = 1;
        List<RentalDTO> rentals = Collections.singletonList(new RentalDTO(1, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 1, (short) 1, customerId));
 
        // Mock the service layer
        when(rentalService.getRentalsByCustomerId(customerId)).thenReturn(rentals);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/rental/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rentalId").value(1));
        // Verify that the service method was called with correct parameters
        verify(rentalService).getRentalsByCustomerId(customerId);
    }
 
// Display top 10 most rented Films
    @Test
    public void testGetTopTenFilms_Success() throws Exception {
        List<String> topFilms = List.of("Film A", "Film B", "Film C");
 
        // Mock the service layer
        when(rentalService.getTopTenFilms()).thenReturn(topFilms);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/rental/toptenfilms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Film A"));
        // Verify that the service method was called
        verify(rentalService).getTopTenFilms();
    }
 
// Display top 10 most rented Films of a Store
    @Test
    public void testGetTopTenFilmsByStoreId_Success() throws Exception {
        Short storeId = 1;
        List<String> topFilms = List.of("Film A", "Film B");
 
        // Mock the service layer
        when(rentalService.getTopTenFilmsByStoreId(storeId)).thenReturn(topFilms);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/rental/toptenfilms/store/{id}", storeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Film A"));
        // Verify that the service method was called with correct parameters
        verify(rentalService).getTopTenFilmsByStoreId(storeId);
    }
 
// Display list of Customers who have not yet returned the  Film
    @Test
    public void testGetDueRentalsByStoreId_Success() throws Exception {
        Short storeId = 1;
        List<RentalDTO> dueRentals = Collections.singletonList(new RentalDTO(2, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 2, (short) 2, (short) 3));
 
        // Mock the service layer
        when(rentalService.getDueRentalsByStoreId(storeId)).thenReturn(dueRentals);
 
        // Perform the GET request and validate the response
        mockMvc.perform(get("/api/rental/due/store/{id}", storeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rentalId").value(2));
        // Verify that the service method was called with correct parameters
        verify(rentalService).getDueRentalsByStoreId(storeId);
    }
 
}