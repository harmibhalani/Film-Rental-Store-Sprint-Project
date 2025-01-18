package com.cg.service;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.dto.CityDTO;
import com.cg.dto.CountryDTO;
import com.cg.dto.CustomerDTO;
 
import com.cg.dto.ManagerStoreDTO;
import com.cg.dto.StaffDTO;
import com.cg.dto.StoreCreateDTO;
import com.cg.dto.StoreDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Address;
import com.cg.model.City;
import com.cg.model.Country;
import com.cg.model.Customer;
import com.cg.model.Staff;
import com.cg.model.Store;
import com.cg.repositories.AddressRepository;
import com.cg.repositories.CityRepository;
 
import com.cg.repositories.CustomerRepository;
import com.cg.repositories.StaffRepository;
import com.cg.repositories.StoreRepository;
 
import org.springframework.transaction.annotation.Transactional;
 
@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
    private StoreRepository storeRepository;
	@Autowired
	private AddressRepository addressRepository;
 
	  @Autowired
	    private StaffRepository staffRepository;
 
 
	  //Add new Store object to DB
	  @Override
	    public StoreDTO addStore(StoreCreateDTO storeCreateDTO) {
	        Address address = addressRepository.findById(storeCreateDTO.getAddressId())
	                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + storeCreateDTO.getAddressId()));
 
	        Staff manager = staffRepository.findById(storeCreateDTO.getManagerId())
	                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + storeCreateDTO.getManagerId()));
 
	        Store store = new Store();
	        store.setAddress(address);
	        store.setManager(manager);
	        store.setLastUpdate(LocalDateTime.now());
 
	        Store savedStore = storeRepository.save(store);
	        return convertToDTO(savedStore);
	    }
 
 
	  //Search Store by City
	  @Override
	    public List<StoreDTO> findStoresByCity(String cityName) {
	        if (cityName == null || cityName.trim().isEmpty()) {
	            throw new ResourceNotFoundException("City name cannot be empty");
	        }
 
	        List<Store> stores = storeRepository.findByAddressCityCityIgnoreCaseContaining(cityName.trim());
	        if (stores.isEmpty()) {
	            throw new ResourceNotFoundException("No stores found in city: " + cityName);
	        }
 
	        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }
 
	  //Search Store by Country
	  @Override
	    public List<StoreDTO> findStoresByCountry(String country) {
	        List<Store> stores = storeRepository.findByAddressCityCountryCountryIgnoreCaseContaining(country);
	        if (stores.isEmpty()) {
	            throw new ResourceNotFoundException("No stores found in country: " + country);
	        }
	        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }
	  //Search Store by phone number
	  @Override
	    public StoreDTO findStoreByPhone(String phone) {
	        Store store = storeRepository.findByAddressPhone(phone);
	        if (store == null) {
	            throw new ResourceNotFoundException("No store found with phone number: " + phone);
	        }
	        return convertToDTO(store);
	    }
	  //Update phone number of a Store
	  @Override
	    public void updateStorePhone(Short storeId, String phone) {
	        Store store = storeRepository.findById(storeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Invalid store ID: " + storeId));
 
	        store.getAddress().setPhone(phone); // Assuming Address is fetched through Store
	        storeRepository.save(store);
	    }

	  //Assign manager to a Store
	  @Override
	    @Transactional
	    public StoreDTO assignManagerToStore(Short storeId, Short managerStaffId) {
	        Store store = storeRepository.findById(storeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Invalid store ID: " + storeId));
 
	        Staff manager = staffRepository.findById(managerStaffId)
	                .orElseThrow(() -> new ResourceNotFoundException("Invalid manager staff ID: " + managerStaffId));
 
	        store.setManager(manager);
	        return convertToDTO(storeRepository.save(store));
	    }
	  //Display all Staff of a Store
	  @Override
	    public List<StaffDTO> findStaffByStoreId(Short storeId) {
	        List<Staff> staffList = storeRepository.findStaffByStoreId(storeId);
	        if (staffList.isEmpty()) {
	            throw new ResourceNotFoundException("No staff found for store ID: " + storeId);
	        }
	        return staffList.stream().map(this::convertStaffToDTO).collect(Collectors.toList());
	    }
	  //Display all Customers of a Store
	  @Override
	    public List<CustomerDTO> findCustomersByStoreId(Short storeId) {
	        List<Customer> customerList = storeRepository.findCustomersByStoreId(storeId);
	        if (customerList.isEmpty()) {
	            throw new ResourceNotFoundException("No customers found for store ID: " + storeId);
	        }
	        return customerList.stream().map(this::convertCustomerToDTO).collect(Collectors.toList());
	    }
	  //Display Manager Details of a Store
	    @Override
	    public StaffDTO findManagerByStoreId(Short storeId) {
	        Staff manager = storeRepository.findManagerByStoreId(storeId);
	        if (manager == null) {
	            throw new ResourceNotFoundException("No manager found for store ID: " + storeId);
	        }
	        return convertStaffToDTO(manager);
	    }
	    //Display Manager details (first name, last name, email,  phone) 
	    //and Store details (Address, City, Phone) of all stores
	    @Override
	    public List<ManagerStoreDTO> getAllManagersDetails() {
	        List<Store> stores = storeRepository.findAll();
	        return stores.stream()
	                .map(store -> new ManagerStoreDTO(
	                        store.getManager().getFirstName(),
	                        store.getManager().getLastName(),
	                        store.getManager().getEmail(),
	                        store.getAddress().getPhone(),
	                        store.getAddress().getAddress(),
	                        store.getAddress().getCity().getCity()
	                ))
	                .collect(Collectors.toList());
	    }

    private StoreDTO convertToDTO(Store store) {
        return new StoreDTO(
            store.getStoreId(),
            store.getManager().getFirstName(),
            store.getAddress().getAddress(),
            store.getAddress().getCity().getCity(),
            store.getAddress().getCity().getCountry().getCountry(),
            store.getAddress().getPhone()
        );
    }
    private StaffDTO convertStaffToDTO(Staff staff) {
        return new StaffDTO(
            staff.getStaffId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getEmail(),
            staff.getUsername(),
            staff.getActive()
        );
    }
    private CustomerDTO convertCustomerToDTO(Customer customer) {
        return new CustomerDTO(
            customer.getCustomerId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail()
        );
    }
 
}