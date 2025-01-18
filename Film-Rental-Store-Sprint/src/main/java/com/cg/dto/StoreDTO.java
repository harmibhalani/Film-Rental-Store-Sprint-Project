package com.cg.dto;
 
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
 
public class StoreDTO {
 
	 @NotNull(message = "Store ID cannot be null")
	    private Short storeId;
 
	    @NotNull(message = "Manager name cannot be null")
	    @Size(min = 2, max = 100, message = "Manager name must be between 2 and 100 characters")
	    private String managerName;
 
	    @NotNull(message = "Address cannot be null")
	    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
	    private String address;
 
	    @NotNull(message = "City cannot be null")
	    @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
	    private String city;
 
	    @NotNull(message = "Country cannot be null")
	    @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters")
	    private String country;
 
	    @NotNull(message = "Phone cannot be null")
	    @Pattern(regexp = "\\+?[0-9\\-]+", message = "Phone must be a valid phone number")
	    private String phone;
 
	    
    public StoreDTO() {
		}
 
	// Constructor
    public StoreDTO(Short storeId, String managerName, String address, 
                    String city, String country, String phone) {
        this.storeId = storeId;
        this.managerName = managerName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }
 
    // Getters and Setters
    public Short getStoreId() {
        return storeId;
    }
 
    public void setStoreId(Short storeId) {
        this.storeId = storeId;
    }
 
    public String getManagerName() {
        return managerName;
    }
 
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
}