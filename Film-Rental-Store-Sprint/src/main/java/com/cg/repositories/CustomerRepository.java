package com.cg.repositories;
 
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.model.Address;
import com.cg.model.Customer;
 
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Short>{
	
	//Search Customers by last name
	List<Customer> findByLastName(String lastName) ;
	
	//Search Customers by first name
	List<Customer> findByFirstName(String firstName);
	
	//Search Customers by email
	List<Customer> findByEmail(String email);
	
	//Search Customers by City
	List<Customer> findByAddress_City_City(String city);
	
	//Search Customers by Country
	List<Customer> findByAddress_City_Country_Country(String countryName);
	
	//Search all active Customers
	 List<Customer> findByActiveTrue();
	
	//Search all Inactive Customers
	 List<Customer> findByActiveFalse();
	
	 //Search Customers by phone number
	 List<Customer> findByAddress_Phone(String phone);
 
	 //Add new Customer Object in DB
	 @Query("SELECT a FROM Address a WHERE a.addressId = :addressId")
    Optional<Address> findAddressById(@Param("addressId") Short addressId);
	 
	 //
	 List<Customer> findByStore_StoreId(Short storeId);
}