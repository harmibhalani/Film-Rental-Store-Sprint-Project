package com.cg.repositories;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.cg.dto.StoreDTO;
import com.cg.model.Address;
import com.cg.model.Customer;
import com.cg.model.Staff;
import com.cg.model.Store;
 
@Repository
public interface StoreRepository extends JpaRepository<Store, Short> {
	//Search Store by City
	   List<Store> findByAddressCityCityIgnoreCaseContaining(String cityName);
	    //Search Store by Country
	    List<Store> findByAddressCityCountryCountryIgnoreCaseContaining(String countryName);
	//Search Store by phone number
	    Store findByAddressPhone(String phone);
	    //Display all Staff of a Store
	    @Query("SELECT s FROM Staff s WHERE s.store.storeId = :storeId")
	    List<Staff> findStaffByStoreId(@Param("storeId") Short storeId);
	    //Display all Customers of a Store
	    @Query("SELECT c FROM Customer c WHERE c.store.storeId = :storeId")
	    List<Customer> findCustomersByStoreId(@Param("storeId") Short storeId);
	    //find staff by ID
	    @Query("SELECT s FROM Staff s WHERE s.staffId = :staffId")
	    Staff findStaffById(@Param("staffId") Short staffId);
	    //Display Manager Details of a Store
	    @Query("SELECT s.manager FROM Store s WHERE s.storeId = :storeId")
	    Staff findManagerByStoreId(@Param("storeId") Short storeId);
	    
	    //
	    @Query("SELECT NEW com.cg.dto.StoreDTO(s.storeId, s.manager.firstName, " +
		           "a.address, c.city, c.country.country, a.phone) " +
		           "FROM Store s " +
		           "JOIN s.address a " +
		           "JOIN a.city c " +
		           "WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :cityName, '%'))")
		    List<StoreDTO> findByCityName(@Param("cityName") String cityName);
	 
		Store findByStoreId(Short storeId);
}