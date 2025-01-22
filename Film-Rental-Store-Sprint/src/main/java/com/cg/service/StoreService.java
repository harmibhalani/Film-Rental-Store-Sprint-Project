package com.cg.service;
 
import java.util.List;
 
import com.cg.dto.CustomerDTO;
 
import com.cg.dto.ManagerStoreDTO;
import com.cg.dto.StaffDTO;
import com.cg.dto.StoreCreateDTO;
import com.cg.dto.StoreDTO;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Customer;
import com.cg.model.Staff;
import com.cg.model.Store;
 
public interface StoreService {
	//Add new Store object to DB
	public StoreDTO addStore(StoreCreateDTO storecreateDTO);
	//Search Store by City
	 List<StoreDTO> findStoresByCity(String cityName);
	//Search Store by Country
    List<StoreDTO> findStoresByCountry(String country);
	   //Search Store by phone number 
	    StoreDTO findStoreByPhone(String phone);
	    //Update phone number of a Store
	    StoreDTO updateStorePhone(Short storeId, String phone);
	    //Assign manager to a Store
	    StoreDTO assignManagerToStore(Short storeId, Short managerStaffId);
	    //Display all Staff of a Store
	    List<StaffDTO> findStaffByStoreId(Short storeId);
	    //Display all Customers of a Store 
	    List<CustomerDTO> findCustomersByStoreId(Short storeId);
	    //Display Manager Details of a Store
	    StaffDTO findManagerByStoreId(Short storeId);
	    //Display Manager details
	    List<ManagerStoreDTO> getAllManagersDetails();
	 // Display all list of Stores
	    List<StoreDTO> findAllStores();
 
}