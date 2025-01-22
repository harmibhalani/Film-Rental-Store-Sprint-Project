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
	                .orElseThrow(() -> new ResourceNotFoundException());
 
	        Staff manager = staffRepository.findById(storeCreateDTO.getManagerId())
	                .orElseThrow(() -> new ResourceNotFoundException());
 
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
	            throw new ResourceNotFoundException();
	        }
 
	        List<Store> stores = storeRepository.findByAddressCityCityIgnoreCaseContaining(cityName.trim());
	        if (stores.isEmpty()) {
	            throw new ResourceNotFoundException();
	        }
 
	        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }
 
	  //Search Store by Country
	  @Override
	    public List<StoreDTO> findStoresByCountry(String country) {
	        List<Store> stores = storeRepository.findByAddressCityCountryCountryIgnoreCaseContaining(country);
	        if (stores.isEmpty()) {
	            throw new ResourceNotFoundException();
	        }
	        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }
	  //Search Store by phone number
	  @Override
	    public StoreDTO findStoreByPhone(String phone) {
	        Store store = storeRepository.findByAddressPhone(phone);
	        if (store == null) {
	            throw new ResourceNotFoundException();
	        }
	        return convertToDTO(store);
	    }
	//Update phone number of a Store  
	  public StoreDTO updateStorePhone(Short storeId, String phone) {
		    Store store = storeRepository.findById(storeId)
		            .orElseThrow(() -> new ResourceNotFoundException());
 
		    // Ensure address is not null before accessing its fields
		    if (store.getAddress() == null) {
		        throw new ResourceNotFoundException();
		    }
 
		    // Update the phone number in the Address object
		    store.getAddress().setPhone(phone);
		    
		    // Save the updated store
		    storeRepository.save(store);
 
		    // Create and return a StoreDTO with updated information
		    String countryName = null;
		    
		    if (store.getAddress().getCity() != null && store.getAddress().getCity().getCountry() != null) {
		        countryName = store.getAddress().getCity().getCountry().getCountry(); // Assuming Country has a getCountryName method
		    }
 
		    return new StoreDTO(
		            store.getStoreId(),
		            store.getManager().getFirstName(), // Assuming Staff has a getFirstName method
		            store.getAddress().getAddress(),
		            store.getAddress().getCity().getCity(), // Accessing city name directly from City object
		            countryName,
		            phone
		    );
		}


	  //Assign manager to a Store
	  @Override
	    @Transactional
	    public StoreDTO assignManagerToStore(Short storeId, Short managerStaffId) {
	        Store store = storeRepository.findById(storeId)
	                .orElseThrow(() -> new ResourceNotFoundException());
 
	        Staff manager = staffRepository.findById(managerStaffId)
	                .orElseThrow(() -> new ResourceNotFoundException());
 
	        store.setManager(manager);
	        return convertToDTO(storeRepository.save(store));
	    }
	  //Display all Staff of a Store
	  @Override
	    public List<StaffDTO> findStaffByStoreId(Short storeId) {
	        List<Staff> staffList = storeRepository.findStaffByStoreId(storeId);
	        if (staffList.isEmpty()) {
	            throw new ResourceNotFoundException();
	        }
	        return staffList.stream().map(this::convertStaffToDTO).collect(Collectors.toList());
	    }
	  //Display all Customers of a Store
	  @Override
	    public List<CustomerDTO> findCustomersByStoreId(Short storeId) {
	        List<Customer> customerList = storeRepository.findCustomersByStoreId(storeId);
	        if (customerList.isEmpty()) {
	            throw new ResourceNotFoundException();
	        }
	        return customerList.stream().map(this::convertCustomerToDTO).collect(Collectors.toList());
	    }
	  //Display Manager Details of a Store
	    @Override
	    public StaffDTO findManagerByStoreId(Short storeId) {
	        Staff manager = storeRepository.findManagerByStoreId(storeId);
	        if (manager == null) {
	            throw new ResourceNotFoundException();
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
 // Display all list of Stores
    @Override
    public List<StoreDTO> findAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
 
}